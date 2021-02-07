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

import com.taro.constants.Constant;
import com.taro.entity.imp.DownloadTemplateEntity;
import com.taro.entity.imp.ImportEntity;
import com.taro.entity.pub.PubDicEntity;
import com.taro.entity.sec.SecRoleEntity;
import com.taro.entity.sec.SecUserEntity;
import com.taro.entity.sec.SecUserRoleRelEntity;
import com.taro.exception.BusinessException;
import com.taro.service.imp.PublicImport;
import com.taro.service.pub.PubDicService;
import com.taro.service.sec.SecRoleService;
import com.taro.service.sec.SecUserRoleRelService;
import com.taro.service.sec.SecUserService;
import com.taro.utils.MD5Utils;
import com.taro.utils.PubImportUtils;
import com.taro.utils.Utility;
import com.taro.utils.UuidUtil;

@Component("ImportOfSecUser")
public class ImportOfSecUser implements PublicImport{

	@Autowired
    private SecRoleService secRoleService; //角色

	@Autowired
    private SecUserService secUserService; //用户

	@Autowired
    private SecUserRoleRelService secUserRoleRelService; //用户角色关系

	@Autowired
    private PubDicService pubDicService; //基础数据
	
	private Map<String, String> getRoleIdNameMap() {
		Map<String, String> roleMap = new HashMap<String, String>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("sysFlag", "1");
		List<SecRoleEntity> baseList = secRoleService.list(queryMap);
		if(CollectionUtils.isNotEmpty(baseList)) {
			roleMap = baseList.stream().collect(Collectors.toMap(SecRoleEntity :: getName, SecRoleEntity :: getId));
		}
		return roleMap;
	}

	private Map<String, String> getUserNameMap() {
		Map<String, String> userMap = new HashMap<String, String>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("sysFlag", "1");
		List<SecUserEntity> baseList = secUserService.list(queryMap);
		if(CollectionUtils.isNotEmpty(baseList)) {
			userMap = baseList.stream().collect(Collectors.toMap(SecUserEntity :: getUsername, SecUserEntity :: getId));
		}
		return userMap;
	}

	private Map<String, String> getSexMap() {
		Map<String, String> sexMap = new HashMap<String, String>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("base_code", "1");
		List<PubDicEntity> baseList = pubDicService.listByBase(queryMap);
		if(CollectionUtils.isNotEmpty(baseList)) {
			sexMap = baseList.stream().collect(Collectors.toMap(PubDicEntity :: getName, PubDicEntity :: getId));
		}
		return sexMap;
	}
	
	@Override
	public ImportEntity importExcel(String fileName,Map<String, List<List<Object>>> excelData,Map<String, Object> parameter) {
		ImportEntity PubImportEntity = new ImportEntity();

        String typeCode = Utility.getParameter(parameter, "typeCode");//类型参数
    	List<String> errorList = new ArrayList<String>();//错误详情日志
    	List<String> sysErrorList = new ArrayList<String>();//系统错误详情日志
        
		PubImportUtils.setSessionAttr(typeCode, "正在加载缓存数据!");
		// 角色数据
		Map<String, String> roleMap = getRoleIdNameMap();
		// 用户数据
		Map<String, String> userMap = getUserNameMap();
		// 性别基础数据
		Map<String, String> sexMap = getSexMap();
		
		PubImportUtils.setSessionAttr(typeCode, "正在解析Excel数据!");
		boolean isBreak = false;//是否终止导入
		List<SecUserEntity> insertList = new ArrayList<SecUserEntity>();//新增List
		List<SecUserRoleRelEntity> insertRelList = new ArrayList<SecUserRoleRelEntity>();//新增用户角色关系List
		//sheet
		String sheetName = "";
		List<List<Object>> sheetList = null;
		//row
		int rowNum = 0;
		List<Object> columnList = null;

		//保存bean
		SecUserEntity saveBean = null;
		SecUserRoleRelEntity saveRelBean = null;
		
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
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 用户名 ]为空,不予导入!");
    				isBreak = true;
    				break;
            	}
            	if(StringUtils.isBlank(String.valueOf(columnList.get(1)).trim())){
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 昵称 ]为空,不予导入!");
    				isBreak = true;
    				break;
            	}
            	if(userMap.containsKey(String.valueOf(columnList.get(0)).trim())){
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 用户名 ]已存在,不予导入!");
    				isBreak = true;
    				break;
            	}

            	saveBean = new SecUserEntity();
            	// id
        		String id = UuidUtil.get32UUID();
        		saveBean.setId(id);
        		// 用户名
        		saveBean.setUsername(String.valueOf(columnList.get(0)).trim());
        		// 昵称
        		saveBean.setNickname(String.valueOf(columnList.get(1)).trim());
        		// 性别
        		String sexName = String.valueOf(columnList.get(2)).trim();
            	if(StringUtils.isBlank(sexName)){
            		sexName = "男";
            	}else if(!sexMap.containsKey(sexName)) {
            		sexName = "男";
    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 性别 ]错误,该列默认为男!");
            	}
        		saveBean.setSex(sexMap.get(sexName));
        		// 生日
        		saveBean.setBirthday(String.valueOf(columnList.get(3)).trim());
        		// 手机号码
        		saveBean.setPhonenum(String.valueOf(columnList.get(4)).trim());
        		// 邮箱
        		saveBean.setEmail(String.valueOf(columnList.get(5)).trim());
            	// 盐
        		String salt = UuidUtil.get32UUID();
        		saveBean.setSalt(salt);
        		saveBean.setPassword(MD5Utils.getSaltMD5("123456", salt));
        		saveBean.setStatus(Constant.USERSTATUS1);

                insertList.add(saveBean);
                
        		// 角色
        		String roles = String.valueOf(columnList.get(6)).trim();
        		if(StringUtils.isNotBlank(roles)) {
        			String[] roleArr = roles.split(",");
        			if(null != roleArr) {
        				for(String role : roleArr) {
        					if(StringUtils.isBlank(role)) {
        						continue;
        					}
        					if(!roleMap.containsKey(role)) {
        	    				errorList.add("sheet[ "+sheetName+" ]中第" + rowNum + "行[ 角色 ]中[ " + role + " ]错误,该关联关系不予导入!");
        	    				continue;
        					}
        					saveRelBean = new SecUserRoleRelEntity();
        					saveRelBean.setUser_pid(id);
        					saveRelBean.setRole_pid(roleMap.get(role));
        					insertRelList.add(saveRelBean);
        				}
        			}
        		}

            }

            if(!isBreak && CollectionUtils.isNotEmpty(insertList)){
                try{
                	for(SecUserEntity insertBean : insertList){
                		secUserService.save(insertBean);
                	}
                }catch(BusinessException e){
        			e.printStackTrace();
        			sysErrorList.add("sheet[ "+sheetName+" ]用户数据插入报错∶ " + e.getMessage() + "!");
    				isBreak = true;
                }catch(Exception e){
        			e.printStackTrace();
        			sysErrorList.add("sheet[ "+sheetName+" ]用户数据插入报错!");
    				isBreak = true;
                }
            }

            if(!isBreak && CollectionUtils.isNotEmpty(insertRelList)){
                try{
                	for(SecUserRoleRelEntity insertBean : insertRelList){
                		secUserRoleRelService.save(insertBean);
                	}
                }catch(BusinessException e){
        			e.printStackTrace();
        			sysErrorList.add("sheet[ "+sheetName+" ]用户角色关联关系数据插入报错∶ " + e.getMessage() + "!");
    				isBreak = true;
                }catch(Exception e){
        			e.printStackTrace();
        			sysErrorList.add("sheet[ "+sheetName+" ]用户角色关联关系数据插入报错!");
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
		downloadTemplateEntity.setFileName("系统用户导入模板");
		downloadTemplateEntity.setSheetName("sheet");
		List<String> headerList = new ArrayList<String>();
		headerList.add("用户名");
		headerList.add("昵称");
		headerList.add("性别");
		headerList.add("生日");
		headerList.add("手机号码");
		headerList.add("邮箱");
		headerList.add("角色");
		List<Object> dataList = new ArrayList<Object>();
		dataList.add("zhangsan");
		dataList.add("张三");
		dataList.add("男");
		dataList.add("20180808");
		dataList.add("13344445555");
		dataList.add("email@qq.com");
		dataList.add("角色1,角色2");
		downloadTemplateEntity.setDataList(dataList);
		return downloadTemplateEntity;
	}
}

