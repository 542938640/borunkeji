package com.taro.service.imp.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.taro.config.ApplicationContextRegister;
import com.taro.entity.imp.DownloadTemplateEntity;
import com.taro.entity.imp.ImportEntity;
import com.taro.exception.BusinessException;
import com.taro.service.imp.ImportService;
import com.taro.service.imp.PublicImport;
import com.taro.utils.ExportExcelUtils;
import com.taro.utils.PubImportUtils;
import com.taro.utils.Utility;

/**
 * ClassName:ImportServiceImp公共导入 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2017年12月13日 上午9:47:57 <br/>
 * @author   张宇唯
 * @since    JDK 1.6
 */

@Service
public class ImportServiceImp implements ImportService {

	@Resource
	private ResourceLoader resourceLoader;

	public ImportEntity importExcel (InputStream in, String fileName, Map<String, Object> paramete){
		ImportEntity pubImportEntity = null;
		try {
	        String typeCode = Utility.getParameter(paramete, "typeCode");//类型参数 
			if(StringUtils.isBlank(typeCode)){
	            throw new BusinessException("请传入类型参数!");  
			}
			
			PublicImport publicImport = (PublicImport)ApplicationContextRegister.getApplicationContext().getBean("ImportOf"+typeCode);
			if(publicImport == null){
	            throw new BusinessException("未找到相对应接口!");  
			}
			
			PubImportUtils.firstSetSessionAttr(typeCode, "正在加载Excel文件"+fileName+"的数据!");
			Map<String,List<List<Object>>> excelData = PubImportUtils.getExcel(in, fileName);
			if(null == excelData){
	            throw new BusinessException("Excel解析错误!");  
			}
			PubImportUtils.setSessionAttr(typeCode, "开始导入!");
			pubImportEntity = publicImport.importExcel(fileName,excelData,paramete);

			pubImportEntity.setStatus("1");
			pubImportEntity.setMsg("导入数据成功");

			PubImportUtils.setSessionAttr(typeCode, "导入结束!");
        } catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
		return pubImportEntity;
	}

	public void downloadTemplate (HttpServletRequest request, HttpServletResponse response, Map<String, Object> paramete) {
		try {
	        String typeCode = Utility.getParameter(paramete, "typeCode");//类型参数 
			if(StringUtils.isBlank(typeCode)){
	            throw new BusinessException("请传入类型参数!");  
			}
			
			PublicImport publicImport = (PublicImport)ApplicationContextRegister.getApplicationContext().getBean("ImportOf"+typeCode);
			if(publicImport == null){
	            throw new BusinessException("未找到相对应接口!");  
			}
			DownloadTemplateEntity templateParam = publicImport.getTemplateParam(paramete);
			if(null != templateParam) {
				String fileName = StringUtils.isBlank(templateParam.getFileName()) ? "模板文件" : templateParam.getFileName();
				String sheetName = StringUtils.isBlank(templateParam.getSheetName()) ? "sheet" : templateParam.getSheetName();
				
				//创建HSSFWorkbook对象(excel的文档对象)
		        HSSFWorkbook wb = new HSSFWorkbook();
		        //建立新的sheet对象（excel的表单）
		        HSSFSheet sheet = wb.createSheet(sheetName);
		        //在sheet里创建行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		        HSSFRow row = null;
		        //创建excel的单元格，参数为列索引，可以是0～255之间的任何一个
		        HSSFCell cell = null;
		        int cellNum = 0;
		        //获取表头样式
		        HSSFCellStyle headerStyle = ExportExcelUtils.getHeaderStyle(wb);
		        
		        List<String> headerList = templateParam.getHeaderList();
		        if(CollectionUtils.isNotEmpty(headerList)) {
	            	row = sheet.createRow(0);
	            	row.setHeight((short)500);
	            	cellNum = 0;
		            //循环表头
		            for(String header : headerList) {
	            		if(StringUtils.isBlank(header)) {
	            			continue;
	            		}
	            		cell = row.createCell(cellNum);
	                    sheet.setColumnWidth(cellNum, header.getBytes().length * 2 * 256);
	            		//单元格应用样式
	                    cell.setCellStyle(headerStyle);
	                    //设置单元格内容
	                    cell.setCellValue(header);
	                    cellNum++;
		            }
		        }

		        //输出Excel文件
		        OutputStream output = null;
				try {
					output = response.getOutputStream();
			        response.reset();
			        response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));//文件名这里可以改
			        response.setContentType("application/octet-stream; charset=utf-8");
			        wb.write(output);
				} catch (IOException e) {
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
				} finally {
			        try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
					}
				}
			}
			
        } catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
  