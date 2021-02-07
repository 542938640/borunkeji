package com.taro.entity.imp;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taro.entity.imp
 * @File: ImpExcelError.java
 * @Description: TODO
 * @Author: 张宇唯
 * @Date: 2017年12月13日
 */
public class ImportEntity {
	private List importList;//导入的List

	private List<String> errorList;//错误日志
	private List<String> sysErrorList;//系统日志
	private Map<String,List<List<Object>>> insertMap;//新增的MAP
	private Map<String,List<List<Object>>> updateMap;//修改的MAP
	private Map<String,List<List<Object>>> notImpMap;//未导的MAP

	private String status;
	private String msg;
	private String id;

	public List getImportList() {
		return importList;
	}

	public void setImportList(List importList) {
		this.importList = importList;
	}

	public List<String> getSysErrorList() {
		return sysErrorList;
	}

	public void setSysErrorList(List<String> sysErrorList) {
		this.sysErrorList = sysErrorList;
	}

	public Map<String, List<List<Object>>> getInsertMap() {
		return insertMap;
	}

	public void setInsertMap(Map<String, List<List<Object>>> insertMap) {
		this.insertMap = insertMap;
	}

	public Map<String, List<List<Object>>> getUpdateMap() {
		return updateMap;
	}

	public void setUpdateMap(Map<String, List<List<Object>>> updateMap) {
		this.updateMap = updateMap;
	}

	public Map<String, List<List<Object>>> getNotImpMap() {
		return notImpMap;
	}

	public void setNotImpMap(Map<String, List<List<Object>>> notImpMap) {
		this.notImpMap = notImpMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

}
