package com.taro.entity.imp;

import java.util.List;


public class DownloadTemplateEntity {
	private String fileName;
	private String sheetName;
	private List<String> headerList;
	private List<Object> dataList;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List<String> getHeaderList() {
		return headerList;
	}
	public void setHeaderList(List<String> headerList) {
		this.headerList = headerList;
	}
	public List<Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}
	
}
