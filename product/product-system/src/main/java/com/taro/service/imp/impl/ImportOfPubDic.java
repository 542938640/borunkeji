package com.taro.service.imp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taro.entity.imp.DownloadTemplateEntity;
import com.taro.entity.imp.ImportEntity;
import com.taro.entity.pub.PubBaseAttributeEntity;
import com.taro.entity.pub.PubBaseEntity;
import com.taro.entity.pub.PubDicEntity;
import com.taro.exception.BusinessException;
import com.taro.service.imp.PublicImport;
import com.taro.service.pub.PubBaseAttributeService;
import com.taro.service.pub.PubBaseService;
import com.taro.service.pub.PubDicService;
import com.taro.utils.ClassRefUtil;
import com.taro.utils.PubImportUtils;
import com.taro.utils.Utility;

@Component("ImportOfPubDic")
public class ImportOfPubDic implements PublicImport{

	@Autowired
    private PubBaseService pubBaseService;//基础数据类型

	@Autowired
    private PubBaseAttributeService pubBaseAttributeService;//基础数据类型属性
	
	@Autowired
    private PubDicService pubDicService;//基础数据
	
	@Override
	public ImportEntity importExcel(String fileName,Map<String, List<List<Object>>> excelData,Map<String, Object> parameter) {
		ImportEntity PubImportEntity = new ImportEntity();

        String typeCode = Utility.getParameter(parameter, "typeCode");//类型参数
    	List<String> errorList = new ArrayList<String>();//错误详情日志
    	List<String> sysErrorList = new ArrayList<String>();//系统错误详情日志
        
		PubImportUtils.setSessionAttr(typeCode, "正在加载缓存数据!");

		PubImportUtils.setSessionAttr(typeCode, "正在解析Excel数据!");
		boolean isBreak = false;//是否终止导入
		List<PubDicEntity> insertList = new ArrayList<PubDicEntity>();//新增List
		//sheet
		String sheetName = "";
		List<List<Object>> sheetList = null;
		//row
		int rowNum = 0;
		List<Object> headerList = null;
		List<Object> columnList = null;

		//保存bean
		PubDicEntity saveBean = null;
		String orderStr = "";

		PubBaseEntity pubBase = null;
		PubBaseAttributeEntity pubBaseAttribute = null;
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("sysFlag", "1");

		Map<String, Object> queryValueMap = new HashMap<String, Object>();
		queryValueMap.put("sysFlag", "1");
		
		List<PubBaseAttributeEntity> pubBaseAttributeList = null;
		List<PubDicEntity> pubDicList = null;
		Map<String, PubBaseAttributeEntity> pubBaseAttributeMap = new HashMap<String, PubBaseAttributeEntity>();
		Map<String, String> pubBaseAttributeNotNullMap = new HashMap<String, String>();
		Map<String, String> headerMap = new HashMap<String, String>();
		
    	for(Iterator<String> iterator = excelData.keySet().iterator(); iterator.hasNext();) {
    		if(isBreak){//终止导入
    			break;
    		}
    		sheetName = iterator.next();//sheet名称
    		if(StringUtils.isBlank(sheetName)) {
				errorList.add("sheet[ "+sheetName+" ]名称为空,不予导入!");
				continue;//sheet为空(或只有表头)就不予处理
    		}
    		
    		sheetList = excelData.get(sheetName);//当前sheet的所有数据
			if(CollectionUtils.isEmpty(sheetList) || sheetList.size() == 1){//第一行是表头
				errorList.add("sheet[ "+sheetName+" ]数据为空,不予导入!");
				continue;//sheet为空(或只有表头)就不予处理
			}
    		
    		pubBase = pubBaseService.getByName(sheetName);
    		if(null == pubBase) {
				errorList.add("sheet[ "+sheetName+" ]查询基础数据类型为空,不予导入!");
				continue;//sheet为空(或只有表头)就不予处理
    		}
    		
    		queryMap.put("base_pid", pubBase.getId());
    		pubBaseAttributeList = pubBaseAttributeService.list(queryMap);
    		pubBaseAttributeMap.clear();
    		pubBaseAttributeNotNullMap.clear();
    		if(CollectionUtils.isNotEmpty(pubBaseAttributeList)) {
    			for(PubBaseAttributeEntity pubBaseAttributeEntity : pubBaseAttributeList) {
    				pubBaseAttributeMap.put(pubBaseAttributeEntity.getName(), pubBaseAttributeEntity);
    				if(StringUtils.isNotBlank(pubBaseAttributeEntity.getRequired_flag()) 
    						&& "1".equals(pubBaseAttributeEntity.getRequired_flag())) {
    					pubBaseAttributeNotNullMap.put(pubBaseAttributeEntity.getName(), "1");
    				}
    			}
    		}
    		
    		headerMap.clear();
    		headerList = sheetList.get(0);
    		for(Object obj : headerList) {
    			headerMap.put(String.valueOf(obj), "1");
    		}
    		
    		for(String key : pubBaseAttributeNotNullMap.keySet()) {
    			if(!headerMap.containsKey(key)) {
    				errorList.add("sheet[ "+sheetName+" ]无[" + key + "]必填列,不予导入!");
    				continue;//sheet为空(或只有表头)就不予处理
    			}
    		}
    		
			PubImportUtils.setSessionAttr(typeCode, "正在解析sheet:[ "+sheetName+" ]的数据!");
            //开始工作(索引从i=1开始表示excel正文，0表示excel表头字段)
            for (int i = 1; i < sheetList.size(); i++) {
            	rowNum = i + 1;
            	columnList = sheetList.get(i);
            	if(CollectionUtils.isEmpty(columnList)){
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行为空,不予导入!");
            		continue;
            	}

            	//判断必填项
            	if(StringUtils.isBlank(String.valueOf(columnList.get(0)).trim())){
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 名称 ]为空,不予导入!");
    				isBreak = true;
    				break;
            	}
            	if(StringUtils.isBlank(String.valueOf(columnList.get(1)).trim())){
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 编码 ]为空,不予导入!");
    				isBreak = true;
    				break;
            	}

            	saveBean = new PubDicEntity();
            	// 基础数据类型
            	saveBean.setBase_pid(pubBase.getId());
            	//名称
            	saveBean.setName(String.valueOf(columnList.get(0)).trim());
            	//编码
            	saveBean.setCode(String.valueOf(columnList.get(1)).trim());
            	//排序
            	orderStr = String.valueOf(columnList.get(2)).trim();
            	if(StringUtils.isBlank(orderStr) || "null".equals(orderStr)) {
            		orderStr = "0";
            	}
            	saveBean.setOrder(Integer.valueOf(orderStr));
            	
            	if(columnList.size() > 3) {
            		Map<String, String> refMap = new HashMap<String, String>();
            		for(int ii = 3, l = columnList.size(); ii < l; ii++) {
            			String header = String.valueOf(headerList.get(ii));
            			String value = String.valueOf(columnList.get(ii));
            			if(StringUtils.isBlank(value)) {
                    		continue;
            			}
            			if(StringUtils.isBlank(header)) {
            				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行第"+ (ii + 1) + "列表头为空,不予导入!");
                    		continue;
            			}
            			if(!pubBaseAttributeMap.containsKey(header)) {
            				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行第"+ (ii + 1) + "列[" + header + "]在基础属性类型中不存在,不予导入!");
                    		continue;
            			}
            			pubBaseAttribute = pubBaseAttributeMap.get(header);
            			if(null == pubBaseAttribute) {
            				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行第"+ (ii + 1) + "列[" + header + "]在基础属性类型中不存在,不予导入!");
                    		continue;
            			}

            			// 下拉框
            			if("pubDicType2".equals(pubBaseAttribute.getInput_control())) {
            				queryValueMap.put("base_pid", pubBaseAttribute.getRefbase_pid());
            				queryValueMap.put("name", value);
            				pubDicList = pubDicService.list(queryValueMap);
            				if(CollectionUtils.isEmpty(pubDicList)) {
                				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行第"+ (ii + 1) + "列[" + header + "]未找到对应基础数据,不予导入!");
                        		continue;
            				}
            				refMap.put(pubBaseAttribute.getStorage_column(), pubDicList.get(0).getId());
            			}else {
            				refMap.put(pubBaseAttribute.getStorage_column(), value);
            			}
            		}
            		ClassRefUtil.setFieldValue(saveBean, refMap);
            	}

                insertList.add(saveBean);
            }

            if(!isBreak && CollectionUtils.isNotEmpty(insertList)){
                try{
                	for(PubDicEntity insertBean : insertList){
                		pubDicService.savePubDic(insertBean);//保存
                	}
                }catch(BusinessException e){
        			e.printStackTrace();
        			sysErrorList.add("sheet[ "+sheetName+" ]数据插入报错∶ " + e.getMessage() + "!");
    				isBreak = true;
                }catch(Exception e){
        			e.printStackTrace();
        			sysErrorList.add("sheet[ "+sheetName+" ]数据插入报错!");
    				isBreak = true;
                }
            }
    	}
		
    	PubImportEntity.setErrorList(errorList);
    	PubImportEntity.setSysErrorList(sysErrorList);
		return PubImportEntity;
	}
	
	@Override
	public DownloadTemplateEntity getTemplateParam (Map<String, Object> paramete) {
		DownloadTemplateEntity downloadTemplateEntity = new DownloadTemplateEntity();
		downloadTemplateEntity.setFileName("基础数据导入模板");
		downloadTemplateEntity.setSheetName("基础数据类型名称");
		List<String> headerList = new ArrayList<String>();
		headerList.add("名称");
		headerList.add("编码");
		headerList.add("排序");
		headerList.add("属性1");
		headerList.add("属性2");
		headerList.add("属性3");
		headerList.add("属性4");
		headerList.add("属性5");
		downloadTemplateEntity.setHeaderList(headerList);
		return downloadTemplateEntity;
	}
}

