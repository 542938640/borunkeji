package com.taro.entity.sec;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SecUserPermissionEntity.java</p>
 *<p>Description:用户权限表 Entity(对应表名:t_sec_user_permission)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
public class SecUserPermissionEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String user_pid;					//用户ID
    private String resource_pid;				//资源ID
    private String type;						//资源类型

    private List<SecUserPermissionEntity> resourceList;
    
	public List<SecUserPermissionEntity> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<SecUserPermissionEntity> resourceList) {
		this.resourceList = resourceList;
	}
	/**
	*<b>Summary:设置用户ID</b>
	* setUser_pid
	* @param user_pid
	*/
    public void setUser_pid(String user_pid){
    	this.user_pid = user_pid;
    }
    /**
	*<b>Summary:获取用户ID</b>
	* getUser_pid
	* @return
	*/
    public String getUser_pid(){
    	return user_pid;
    }
	/**
	*<b>Summary:设置资源ID</b>
	* setResource_pid
	* @param resource_pid
	*/
    public void setResource_pid(String resource_pid){
    	this.resource_pid = resource_pid;
    }
    /**
	*<b>Summary:获取资源ID</b>
	* getResource_pid
	* @return
	*/
    public String getResource_pid(){
    	return resource_pid;
    }
	/**
	*<b>Summary:设置资源类型</b>
	* setType
	* @param type
	*/
    public void setType(String type){
    	this.type = type;
    }
    /**
	*<b>Summary:获取资源类型</b>
	* getType
	* @return
	*/
    public String getType(){
    	return type;
    }
}