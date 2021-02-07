package com.taro.service.pub;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taro.entity.pub.PubExportConfigEntity;
import com.taro.service.IService;

/**
 *<p>Title:ExportCofigService.java</p>
 *<p>Description:公共导出配置表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public interface PubExportConfigService extends IService<PubExportConfigEntity> {
	
	void exportExcel(HttpServletRequest request, HttpServletResponse response, String exportTypeCode, 
			String fileName, String sheetName, String isQueryData, Map<String, String> paramete);
	
	PubExportConfigEntity saveExportConfig(PubExportConfigEntity model);
	
}