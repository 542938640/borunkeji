package com.taro.entity.sec;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SecRolePermissionEntity.java</p>
 *<p>Description:角色权限表 Entity(对应表名:t_sec_role_permission)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
public class SecRolePermissionEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String role_pid;					//角色ID
    private String resource_pid;				//资源ID
    private String type;						//资源类型

    private List<SecRolePermissionEntity> resourceList;
    
	public List<SecRolePermissionEntity> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<SecRolePermissionEntity> resourceList) {
		this.resourceList = resourceList;
	}
	/**
	*<b>Summary:设置角色ID</b>
	* setRole_pid
	* @param role_pid
	*/
    public void setRole_pid(String role_pid){
    	this.role_pid = role_pid;
    }
    /**
	*<b>Summary:获取角色ID</b>
	* getRole_pid
	* @return
	*/
    public String getRole_pid(){
    	return role_pid;
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