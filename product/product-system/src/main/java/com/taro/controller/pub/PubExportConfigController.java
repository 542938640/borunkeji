package com.taro.controller.pub;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taro.common.DataSetObject;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.pub.PubExportConfigEntity;
import com.taro.exception.BusinessException;
import com.taro.service.pub.PubExportConfigService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:PubExportCofigController.java</p>
 *<p>Description:公共导出配置表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@RestController
@RequestMapping(value="/pubExportConfig")
public class PubExportConfigController extends AbstractController<PubExportConfigEntity>{

	public final static String MODULE = "公共导出配置";

	public final static String ENTITY = "PubExportConfigEntity";
	
	@Autowired
	private PubExportConfigService pubExportCofigService;
	
	@Override
    protected PubExportConfigService getService () {
        return pubExportCofigService;
    }

	@Override
    public String getModule() {
		return MODULE;
	}

    /**
     * 模板公共导出接口
     * @param response
     * @param request
     * @param paramete
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportTemplate(HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value = "exportTypeCode") String exportTypeCode,
    		@RequestParam(value = "fileName", defaultValue = "") String fileName,
    		@RequestParam(value = "sheetName", defaultValue = "") String sheetName,
    		@RequestParam(value = "isQueryData", defaultValue = "1") String isQueryData,
    		@RequestParam Map<String, String> paramete) throws Exception {
    	getService().exportExcel(request, response, exportTypeCode, fileName, sheetName, isQueryData, paramete);
        return null;
    }

	@ApiOperation(value = "根据实例id保存详情", notes = "根据实例id保存详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveExportConfig",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveExportConfig(@RequestBody PubExportConfigEntity model) {
		
		Set<ConstraintViolation<PubExportConfigEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveExportConfig(model);
		return new DataSetObject<PubExportConfigEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

}