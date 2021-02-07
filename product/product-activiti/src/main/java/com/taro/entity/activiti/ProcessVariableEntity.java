package com.taro.entity.activiti;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:ProcessVariableEntity.java</p>
 *<p>Description:流程变量 Entity(对应表名:t_process_variable)</p>
 *@author Ann
 *@version 1.0
 *@Automatically generate by Coder in 2019-03-19 14:45
 */
public class ProcessVariableEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String task_id;		//
    private String task_name;		//
    private String key;		//
    private String value;		//

	/**
	*<b>Summary:设置</b>
	* setTask_id
	* @param task_id
	*/
    public void setTask_id(String task_id){
    	this.task_id = task_id;
    }
    /**
	*<b>Summary:获取</b>
	* getTask_id
	* @return
	*/
    public String getTask_id(){
    	return task_id;
    }
	/**
	*<b>Summary:设置</b>
	* setTask_name
	* @param task_name
	*/
    public void setTask_name(String task_name){
    	this.task_name = task_name;
    }
    /**
	*<b>Summary:获取</b>
	* getTask_name
	* @return
	*/
    public String getTask_name(){
    	return task_name;
    }
	/**
	*<b>Summary:设置</b>
	* setKey
	* @param key
	*/
    public void setKey(String key){
    	this.key = key;
    }
    /**
	*<b>Summary:获取</b>
	* getKey
	* @return
	*/
    public String getKey(){
    	return key;
    }
	/**
	*<b>Summary:设置</b>
	* setValue
	* @param value
	*/
    public void setValue(String value){
    	this.value = value;
    }
    /**
	*<b>Summary:获取</b>
	* getValue
	* @return
	*/
    public String getValue(){
    	return value;
    }
}