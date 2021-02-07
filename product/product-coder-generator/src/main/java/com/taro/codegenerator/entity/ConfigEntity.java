package com.taro.codegenerator.entity;

public class ConfigEntity {
	private String db_type;				// 数据库类型
	private String db_host;				// 数据库主机名或ip地址
	private String db_port;				// 数据库端口
	private String db_database;			// 数据库名
	private String db_username;			// 数据库用户名
	private String db_password;			// 数据库密码
	
	private String table_name;			// 表名
	private String java_url;			// java文件生成路径
	private String vue_url;				// vue文件生成路径
	private String package_name;		// 包名
	private String module_name;			// 模块名
	private String class_name;			// 类名
	private String author;				// 作者
	private String generator_time;		// 生成时间
	
	public String getDb_type() {
		return db_type;
	}
	public void setDb_type(String db_type) {
		this.db_type = db_type;
	}
	public String getDb_host() {
		return db_host;
	}
	public void setDb_host(String db_host) {
		this.db_host = db_host;
	}
	public String getDb_port() {
		return db_port;
	}
	public void setDb_port(String db_port) {
		this.db_port = db_port;
	}
	public String getDb_database() {
		return db_database;
	}
	public void setDb_database(String db_database) {
		this.db_database = db_database;
	}
	public String getDb_username() {
		return db_username;
	}
	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}
	public String getDb_password() {
		return db_password;
	}
	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getJava_url() {
		return java_url;
	}
	public void setJava_url(String java_url) {
		this.java_url = java_url;
	}
	public String getVue_url() {
		return vue_url;
	}
	public void setVue_url(String vue_url) {
		this.vue_url = vue_url;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenerator_time() {
		return generator_time;
	}
	public void setGenerator_time(String generator_time) {
		this.generator_time = generator_time;
	}
	
}
