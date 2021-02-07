package com.taro.entity.activiti;

import java.io.Serializable;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:ProcessTaskEntity.java</p>
 *<p>Description:流程任务表 Entity(对应表名:t_process_task)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 18:34
 */
public class ProcessTaskEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String process_pid;			//流程id
    private String task_name;			//任务名称
    private String task_id;				//任务id
    private String company;				//单位
    private String role_pid;			//角色id
    private String role_name;			//角色名称
    private String user_pid;			//用户id
    private String user_name;			//用户名称
    private String pc_url;				//pc页面地址
    private String app_url;				//app页面地址

    private String process_name;		//流程名称
    private String busi_key;			//业务id
    private String title;				//代办标题
    private String idea;				//结论
    private String idea_message;		//意见
    private String operation_user;		//执行人
    private String operation_user_name;	//执行人_名称
    private String operation_time;		//执行时间
    private String assignee;			//执行人id
    private String assignee_name;		//执行人名称
    private String start_time;			//开始时间
    private String end_time;			//结束时间
    private String comment;				//信息
    
	public String getOperation_user_name() {
		return operation_user_name;
	}
	public void setOperation_user_name(String operation_user_name) {
		this.operation_user_name = operation_user_name;
	}
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getAssignee_name() {
		return assignee_name;
	}
	public void setAssignee_name(String assignee_name) {
		this.assignee_name = assignee_name;
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
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBusi_key() {
		return busi_key;
	}
	public void setBusi_key(String busi_key) {
		this.busi_key = busi_key;
	}
	public String getPc_url() {
		return pc_url;
	}
	public void setPc_url(String pc_url) {
		this.pc_url = pc_url;
	}
	public String getApp_url() {
		return app_url;
	}
	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	*<b>Summary:设置流程id</b>
	* setProcess_pid
	* @param process_pid
	*/
    public void setProcess_pid(String process_pid){
    	this.process_pid = process_pid;
    }
    /**
	*<b>Summary:获取流程id</b>
	* getProcess_pid
	* @return
	*/
    public String getProcess_pid(){
    	return process_pid;
    }
	/**
	*<b>Summary:设置任务名称</b>
	* setTask_name
	* @param task_name
	*/
    public void setTask_name(String task_name){
    	this.task_name = task_name;
    }
    /**
	*<b>Summary:获取任务名称</b>
	* getTask_name
	* @return
	*/
    public String getTask_name(){
    	return task_name;
    }
	/**
	*<b>Summary:设置任务id</b>
	* setTask_id
	* @param task_id
	*/
    public void setTask_id(String task_id){
    	this.task_id = task_id;
    }
    /**
	*<b>Summary:获取任务id</b>
	* getTask_id
	* @return
	*/
    public String getTask_id(){
    	return task_id;
    }
	/**
	*<b>Summary:设置角色id</b>
	* setRole_pid
	* @param role_pid
	*/
    public void setRole_pid(String role_pid){
    	this.role_pid = role_pid;
    }
    /**
	*<b>Summary:获取角色id</b>
	* getRole_pid
	* @return
	*/
    public String getRole_pid(){
    	return role_pid;
    }
	/**
	*<b>Summary:设置用户id</b>
	* setUser_pid
	* @param user_pid
	*/
    public void setUser_pid(String user_pid){
    	this.user_pid = user_pid;
    }
    /**
	*<b>Summary:获取用户id</b>
	* getUser_pid
	* @return
	*/
    public String getUser_pid(){
    	return user_pid;
    }
}