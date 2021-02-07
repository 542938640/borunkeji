package com.taro.entity;

import java.io.Serializable;

/**
 *<p>Title:ProcessEntity.java</p>
 *<p>Description:流程表 Entity(对应表名:t_process)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 16:55
 */
public class ProcessDataEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String flow_key;					//流程id
    private String busi_key;					//业务id
	private String task_id;						//任务id
    private String task_def;					//任务环节id
    private String task_name;					//任务名称
    private String flow_state;					//流程状态
    private String flow_task_name;				//流程环节名称
    private String idea;						//结论
    private String idea_message;				//意见
    private String operation_user;				//操作人
    private String operation_time;				//操作时间
    
	public String getOperation_user() {
		return operation_user;
	}
	public void setOperation_user(String operation_user) {
		this.operation_user = operation_user;
	}
	public String getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public String getIdea_message() {
		return idea_message;
	}
	public void setIdea_message(String idea_message) {
		this.idea_message = idea_message;
	}
	public String getFlow_state() {
		return flow_state;
	}
	public void setFlow_state(String flow_state) {
		this.flow_state = flow_state;
	}
	public String getFlow_task_name() {
		return flow_task_name;
	}
	public void setFlow_task_name(String flow_task_name) {
		this.flow_task_name = flow_task_name;
	}
	public String getFlow_key() {
		return flow_key;
	}
	public void setFlow_key(String flow_key) {
		this.flow_key = flow_key;
	}
	public String getBusi_key() {
		return busi_key;
	}
	public void setBusi_key(String busi_key) {
		this.busi_key = busi_key;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getTask_def() {
		return task_def;
	}
	public void setTask_def(String task_def) {
		this.task_def = task_def;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
}