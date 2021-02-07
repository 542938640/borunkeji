package com.taro.entity.notice;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:NoticeObjectEntity.java</p>
 *<p>Description:信息发布对象 Entity(对应表名:t_notice_object)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:15:50
 */
public class NoticeObjectEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 消息主键
    private String notice_pid;
	// 租户id
    private String tenants_pid;

	// 租户名称
    private String tenants_name;

	/**
	 * <b>Summary:设置消息主键</b>
	 * setNotice_pid
	 * @param notice_pid
	 */
    public void setNotice_pid(String notice_pid) {
    	this.notice_pid = notice_pid;
    }
    
    /**
	 * <b>Summary:获取消息主键</b>
	 * getNotice_pid
	 * @return String
	 */
    public String getNotice_pid() {
    	return notice_pid;
    }
	/**
	 * <b>Summary:设置租户id</b>
	 * setTenants_pid
	 * @param tenants_pid
	 */
    public void setTenants_pid(String tenants_pid) {
    	this.tenants_pid = tenants_pid;
    }
    
    /**
	 * <b>Summary:获取租户id</b>
	 * getTenants_pid
	 * @return String
	 */
    public String getTenants_pid() {
    	return tenants_pid;
    }

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

}