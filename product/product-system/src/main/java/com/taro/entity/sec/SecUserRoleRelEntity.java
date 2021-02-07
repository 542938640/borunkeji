package com.taro.entity.sec;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SecuserRoleRelEntity.java</p>
 *<p>Description:用户角色关系表 Entity(对应表名:t_sec_user_role_rel)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public class SecUserRoleRelEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String user_pid;								//用户ID
    private String role_pid;								//角色ID

    private String user_name;								//用户名称
    private String role_name;								//角色名称
    
    
	public String getUser_pid() {
		return user_pid;
	}
	public void setUser_pid(String user_pid) {
		this.user_pid = user_pid;
	}
	public String getRole_pid() {
		return role_pid;
	}
	public void setRole_pid(String role_pid) {
		this.role_pid = role_pid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
    
    
}