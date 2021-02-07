package com.taro.controller.imp;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.constants.Constant;
import com.taro.entity.imp.ImportEntity;
import com.taro.exception.BusinessException;
import com.taro.service.imp.ImportService;
import com.taro.utils.Utility;

/**
 *<p>Title:ImportController.java公共导入接口</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2017-12-13 10:02
 */
@RestController
@RequestMapping(value="/import")
public class ImportController{
	@Autowired
	private ImportService importService;
	
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	
	/**
	 * impOrgExcel:组织机构导入. <br/>
	 * @author 张宇唯
	 * @return
	 */
	@RequestMapping(value = "/importExcel")
    public String importExcel(HttpServletRequest request,@RequestParam Map<String, Object> paramete) throws Exception {  
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream in =null;  
        MultipartFile file = multipartRequest.getFile("file");  
        if(file==null||file.isEmpty()){
            throw new Exception("文件不存在!");  
        }  
        ImportEntity importEntity = null;
		try {
			in = file.getInputStream(); 
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			if (excel2003L.equals(fileType) || excel2007U.equals(fileType)) {
				importEntity = importService.importExcel(in, fileName, paramete);
			} else{
				throw new Exception("解析的文件格式有误！");
			}
        } catch (Exception e) {
        	e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally{
			in.close();
		};
        return new DataSetObject<ImportEntity>(importEntity).toJson();  
	}

    /**
     * getImportStr:获取进度文字接口
     * @param impName进度Str
     * @return
     */
	@RequestMapping(value = "/getImportStr")
	public String getImportStr(@RequestParam Map<String, Object> paramete) {
		String typeCode = Utility.getParameter(paramete, "typeCode");
		if(StringUtils.isBlank(typeCode)){
			typeCode = "";
		}
		Session session = SecurityUtils.getSubject().getSession();
		String importStr = String.valueOf(session.getAttribute("import"+typeCode+"Str"));
		return new Message(Constant.STATUS_OK,importStr).toJson();
	}

	/**
	 * downloadTemplate:下载模板文件. <br/>
	 * @author 张宇唯
	 * @return
	 */
	@RequestMapping(value = "/downloadTemplate")
	public ModelAndView downloadTemplate(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> paramete) {
		importService.downloadTemplate(request, response, paramete);
        return null;
	}
}