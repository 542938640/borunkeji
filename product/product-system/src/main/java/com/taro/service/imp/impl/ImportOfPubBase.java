package com.taro.service.imp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taro.entity.imp.DownloadTemplateEntity;
import com.taro.entity.imp.ImportEntity;
import com.taro.entity.pub.PubBaseEntity;
import com.taro.exception.BusinessException;
import com.taro.service.imp.PublicImport;
import com.taro.service.pub.PubBaseService;
import com.taro.utils.PubImportUtils;
import com.taro.utils.Utility;

/**
 * ClassName:testImport <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2017年12月13日 下午4:12:47 <br/>
 * @author   zhangyuwei
 * @since    JDK 1.6
 */
@Component("ImportOfPubBase")
public class ImportOfPubBase implements PublicImport{

	@Autowired
    private PubBaseService pubBaseService;//基础数据
	
	
	private Map<String, String> getBaseIdNameMap() {
		Map<String, String> baseMap = new HashMap<String, String>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("sysFlag", "1");
		List<PubBaseEntity> baseList = pubBaseService.list(queryMap);
		if(CollectionUtils.isNotEmpty(baseList)) {
			baseMap = baseList.stream().collect(Collectors.toMap(PubBaseEntity :: getName, PubBaseEntity :: getId));
		}
		return baseMap;
	}
	
	@Override
	public ImportEntity importExcel(String fileName,Map<String, List<List<Object>>> excelData,Map<String, Object> parameter) {
		ImportEntity PubImportEntity = new ImportEntity();

        String typeCode = Utility.getParameter(parameter, "typeCode");//类型参数
    	List<String> errorList = new ArrayList<String>();//错误详情日志
    	List<String> sysErrorList = new ArrayList<String>();//系统错误详情日志
        
		PubImportUtils.setSessionAttr(typeCode, "正在加载缓存数据!");
		//基础数据
		Map<String, String> baseMap = getBaseIdNameMap();

		PubImportUtils.setSessionAttr(typeCode, "正在解析Excel数据!");
		boolean isBreak = false;//是否终止导入
		List<PubBaseEntity> insertList = new ArrayList<PubBaseEntity>();//新增List
		//sheet
		String sheetName = "";
		List<List<Object>> sheetList = null;
		//row
		int rowNum = 0;
		List<Object> columnList = null;

		//保存bean
		PubBaseEntity saveBean = null;
		String orderStr = "";
		String parent_name = "";
		
    	for(Iterator<String> iterator = excelData.keySet().iterator(); iterator.hasNext();) {
    		if(isBreak){//终止导入
    			break;
    		}
    		sheetName = iterator.next();//sheet名称
    		sheetList = excelData.get(sheetName);//当前sheet的所有数据
			if(CollectionUtils.isEmpty(sheetList) || sheetList.size() == 1){//第一行是表头
				errorList.add("sheet[ "+sheetName+" ]为空,不予导入!");
				continue;//sheet为空(或只有表头)就不予处理
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

            	saveBean = new PubBaseEntity();
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
            	
            	parent_name = String.valueOf(columnList.get(3)).trim();
            	if(StringUtils.isBlank(parent_name) || "null".equals(parent_name)) {
            		if(baseMap.containsKey(parent_name)) {
            			saveBean.setParent_pid(baseMap.get(parent_name));
            		}else {
            			saveBean.setParent_pid("-1");
            		}
            	}

                insertList.add(saveBean);
            }

            if(!isBreak && CollectionUtils.isNotEmpty(insertList)){
                try{
                	for(PubBaseEntity insertBean : insertList){
                		pubBaseService.savePubBase(insertBean);//保存
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
		return null;
	}
}

