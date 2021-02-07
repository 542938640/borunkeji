package com.taro.entity.sec;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SecTenantsPermissionEntity.java</p>
 *<p>Description: Entity(对应表名:t_sec_tenants_permission)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-11 23:14:15
 */
public class SecTenantsPermissionEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户ID
    private String tenants_pid;
	// 资源ID
    private String resource_pid;
	// 资源类型
    private String type;

    private List<SecTenantsPermissionEntity> resourceList;
    
	/**
	 * <b>Summary:设置租户ID</b>
	 * setTenants_pid
	 * @param tenants_pid
	 */
    public void setTenants_pid(String tenants_pid) {
    	this.tenants_pid = tenants_pid;
    }
    
    /**
	 * <b>Summary:获取租户ID</b>
	 * getTenants_pid
	 * @return String
	 */
    public String getTenants_pid() {
    	return tenants_pid;
    }
	/**
	 * <b>Summary:设置资源ID</b>
	 * setResource_pid
	 * @param resource_pid
	 */
    public void setResource_pid(String resource_pid) {
    	this.resource_pid = resource_pid;
    }
    
    /**
	 * <b>Summary:获取资源ID</b>
	 * getResource_pid
	 * @return String
	 */
    public String getResource_pid() {
    	return resource_pid;
    }
	/**
	 * <b>Summary:设置资源类型</b>
	 * setType
	 * @param type
	 */
    public void setType(String type) {
    	this.type = type;
    }
    
    /**
	 * <b>Summary:获取资源类型</b>
	 * getType
	 * @return String
	 */
    public String getType() {
    	return type;
    }

	public List<SecTenantsPermissionEntity> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<SecTenantsPermissionEntity> resourceList) {
		this.resourceList = resourceList;
	}

}