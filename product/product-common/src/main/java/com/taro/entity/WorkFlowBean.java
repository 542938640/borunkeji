package com.taro.entity;

public class WorkFlowBean {

	private Integer is_start;//是否启动
	private String start_user;//启动人
	private String busi_key;//
	private String flow_key;//
	private String assignee;
	private String title;
	private String variables_json;//
	private String comment;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getIs_start() {
		return is_start;
	}
	public void setIs_start(Integer is_start) {
		this.is_start = is_start;
	}
	public String getStart_user() {
		return start_user;
	}
	public void setStart_user(String start_user) {
		this.start_user = start_user;
	}
	public String getBusi_key() {
		return busi_key;
	}
	public void setBusi_key(String busi_key) {
		this.busi_key = busi_key;
	}
	public String getFlow_key() {
		return flow_key;
	}
	public void setFlow_key(String flow_key) {
		this.flow_key = flow_key;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVariables_json() {
		return variables_json;
	}
	public void setVariables_json(String variables_json) {
		this.variables_json = variables_json;
	}
	
	
}
