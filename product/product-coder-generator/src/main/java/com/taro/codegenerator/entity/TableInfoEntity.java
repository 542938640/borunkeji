package com.taro.codegenerator.entity;

public class TableInfoEntity {
	private String table_name;		// 表名称
	private String table_type;		// 表类型
	private String table_cat;		// 表所属数据库
	private String table_schem;		// 表所属用户名
	private String remarks;			// 表备注
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_type() {
		return table_type;
	}
	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}
	public String getTable_cat() {
		return table_cat;
	}
	public void setTable_cat(String table_cat) {
		this.table_cat = table_cat;
	}
	public String getTable_schem() {
		return table_schem;
	}
	public void setTable_schem(String table_schem) {
		this.table_schem = table_schem;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
