package com.taro.service.imp;

import java.util.List;
import java.util.Map;

import com.taro.entity.imp.DownloadTemplateEntity;
import com.taro.entity.imp.ImportEntity;

/**
 *<p>Title:PubImport.java</p>
 *<p>Description:公共导入接口</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2017-12-13 09:13
 */
public interface PublicImport{
    /**
     * importExcel:解析Excel 封装数据. <br/>
     * @author 张宇唯
     * @param fileName 文件名(用作报错提示)
     * @param cacheMap 缓存数据
     * @param excelData Excel数据
     * @param parameter 业务所需参数
     * @return
     */
	ImportEntity importExcel(String fileName, Map<String,List<List<Object>>> excelData, Map<String, Object> parameter);

    /**
     * getTemplateParam:获得下载模板配置文件. <br/>
     * @author 张宇唯
     * @param parameter 业务所需参数
     * @return
     */
	DownloadTemplateEntity getTemplateParam (Map<String, Object> paramete);
}