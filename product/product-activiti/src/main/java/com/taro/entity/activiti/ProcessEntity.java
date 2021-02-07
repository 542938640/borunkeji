package com.taro.entity.activiti;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:ProcessEntity.java</p>
 *<p>Description:流程表 Entity(对应表名:t_process)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 16:55
 */
public class ProcessEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String name;						//流程名称
    private String flow_key;					//流程定义id
    private String procde_pid;					//流程定义数据表id
    private String deployment_pid;				//部署信息表id

    private String isDeleteTask;				//是否需要删除taskList
    
    //流程定义属性
    private String pd_id;						//流程定义ID
    private String pd_name;						//流程定义名称
    private String pd_key;						//流程定义的key
    private int pd_version;						//流程定义的版本
    private String pd_resourceName;				//资源名称bpmn文件
    private String pd_diagramResourceName;		//资源名称png文件
    private String pd_deploymentId;				//部署对象ID

    private List<ProcessTaskEntity> taskList;	//任务list
    
	public String getIsDeleteTask() {
		return isDeleteTask;
	}
	public void setIsDeleteTask(String isDeleteTask) {
		this.isDeleteTask = isDeleteTask;
	}
	public List<ProcessTaskEntity> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<ProcessTaskEntity> taskList) {
		this.taskList = taskList;
	}
	public String getPd_id() {
		return pd_id;
	}
	public void setPd_id(String pd_id) {
		this.pd_id = pd_id;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public String getPd_key() {
		return pd_key;
	}
	public void setPd_key(String pd_key) {
		this.pd_key = pd_key;
	}
	public int getPd_version() {
		return pd_version;
	}
	public void setPd_version(int pd_version) {
		this.pd_version = pd_version;
	}
	public String getPd_resourceName() {
		return pd_resourceName;
	}
	public void setPd_resourceName(String pd_resourceName) {
		this.pd_resourceName = pd_resourceName;
	}
	public String getPd_diagramResourceName() {
		return pd_diagramResourceName;
	}
	public void setPd_diagramResourceName(String pd_diagramResourceName) {
		this.pd_diagramResourceName = pd_diagramResourceName;
	}
	public String getPd_deploymentId() {
		return pd_deploymentId;
	}
	public void setPd_deploymentId(String pd_deploymentId) {
		this.pd_deploymentId = pd_deploymentId;
	}
	/**
	*<b>Summary:设置流程名称</b>
	* setName
	* @param name
	*/
    public void setName(String name){
    	this.name = name;
    }
    /**
	*<b>Summary:获取流程名称</b>
	* getName
	* @return
	*/
    public String getName(){
    	return name;
    }
	/**
	*<b>Summary:设置流程定义id</b>
	* setFlow_key
	* @param flow_key
	*/
    public void setFlow_key(String flow_key){
    	this.flow_key = flow_key;
    }
    /**
	*<b>Summary:获取流程定义id</b>
	* getFlow_key
	* @return
	*/
    public String getFlow_key(){
    	return flow_key;
    }
	/**
	*<b>Summary:设置流程定义数据表id</b>
	* setProcdepid
	* @param procdepid
	*/
    public void setProcde_pid(String procde_pid){
    	this.procde_pid = procde_pid;
    }
    /**
	*<b>Summary:获取流程定义数据表id</b>
	* getProcdepid
	* @return
	*/
    public String getProcde_pid(){
    	return procde_pid;
    }
	/**
	*<b>Summary:设置部署信息表id</b>
	* setDeployment_pid
	* @param deployment_pid
	*/
    public void setDeployment_pid(String deployment_pid){
    	this.deployment_pid = deployment_pid;
    }
    /**
	*<b>Summary:获取部署信息表id</b>
	* getDeployment_pid
	* @return
	*/
    public String getDeployment_pid(){
    	return deployment_pid;
    }
}