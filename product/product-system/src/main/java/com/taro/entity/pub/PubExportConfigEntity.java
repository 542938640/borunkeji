package com.taro.entity.pub;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:ExportCofigEntity.java</p>
 *<p>Description:公共导出配置表 Entity(对应表名:t_pub_export_cofig)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public class PubExportConfigEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String code;							//编码
    private String name;							//名称
    private String column_list;						//表头List
    private String query_interface;					//查询接口
    private String query_param;						//查询参数
    private String query_method;					//查询方法
    
    private String query_method_name;				//查询方法名称
    
	public String getQuery_method_name() {
		return query_method_name;
	}
	public void setQuery_method_name(String query_method_name) {
		this.query_method_name = query_method_name;
	}
	public String getQuery_method() {
		return query_method;
	}
	public void setQuery_method(String query_method) {
		this.query_method = query_method;
	}
	/**
	*<b>Summary:设置编码</b>
	* setCode
	* @param code
	*/
    public void setCode(String code){
    	this.code = code;
    }
    /**
	*<b>Summary:获取编码</b>
	* getCode
	* @return
	*/
    public String getCode(){
    	return code;
    }
	/**
	*<b>Summary:设置名称</b>
	* setName
	* @param name
	*/
    public void setName(String name){
    	this.name = name;
    }
    /**
	*<b>Summary:获取名称</b>
	* getName
	* @return
	*/
    public String getName(){
    	return name;
    }
	/**
	*<b>Summary:设置表头List</b>
	* setColumn_list
	* @param column_list
	*/
    public void setColumn_list(String column_list){
    	this.column_list = column_list;
    }
    /**
	*<b>Summary:获取表头List</b>
	* getColumn_list
	* @return
	*/
    public String getColumn_list(){
    	return column_list;
    }
	/**
	*<b>Summary:设置查询接口</b>
	* setQuery_interface
	* @param query_interface
	*/
    public void setQuery_interface(String query_interface){
    	this.query_interface = query_interface;
    }
    /**
	*<b>Summary:获取查询接口</b>
	* getQuery_interface
	* @return
	*/
    public String getQuery_interface(){
    	return query_interface;
    }
	/**
	*<b>Summary:设置查询参数</b>
	* setQuery_param
	* @param query_param
	*/
    public void setQuery_param(String query_param){
    	this.query_param = query_param;
    }
    /**
	*<b>Summary:获取查询参数</b>
	* getQuery_param
	* @return
	*/
    public String getQuery_param(){
    	return query_param;
    }
}