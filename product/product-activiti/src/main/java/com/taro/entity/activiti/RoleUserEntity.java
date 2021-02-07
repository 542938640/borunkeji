package com.taro.entity.activiti;

import java.io.Serializable;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:ProcessEntity.java</p>
 *<p>Description:流程表 Entity(对应表名:t_process)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 16:55
 */
public class RoleUserEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String role_pid;						//角色id
    private String user_pid;						//用户id
    
	public String getRole_pid() {
		return role_pid;
	}
	public void setRole_pid(String role_pid) {
		this.role_pid = role_pid;
	}
	public String getUser_pid() {
		return user_pid;
	}
	public void setUser_pid(String user_pid) {
		this.user_pid = user_pid;
	}
    
}