package com.taro.entity.device;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:DeviceOutletsEntity.java</p>
 *<p>Description:设备网点信息 Entity(对应表名:t_device_outlets)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:18
 */
public class DeviceOutletsEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 名称
    private String name;
	// 地址
    private String address;
	// 联系人
    private String contacts;
	// 手机
    private String phone;
	// 电话
    private String telephone;

    private String tenants_name;
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
	/**
	 * <b>Summary:设置名称</b>
	 * setName
	 * @param name
	 */
    public void setName(String name) {
    	this.name = name;
    }
    
    /**
	 * <b>Summary:获取名称</b>
	 * getName
	 * @return String
	 */
    public String getName() {
    	return name;
    }
	/**
	 * <b>Summary:设置地址</b>
	 * setAddress
	 * @param address
	 */
    public void setAddress(String address) {
    	this.address = address;
    }
    
    /**
	 * <b>Summary:获取地址</b>
	 * getAddress
	 * @return String
	 */
    public String getAddress() {
    	return address;
    }
	/**
	 * <b>Summary:设置联系人</b>
	 * setContacts
	 * @param contacts
	 */
    public void setContacts(String contacts) {
    	this.contacts = contacts;
    }
    
    /**
	 * <b>Summary:获取联系人</b>
	 * getContacts
	 * @return String
	 */
    public String getContacts() {
    	return contacts;
    }
	/**
	 * <b>Summary:设置手机</b>
	 * setPhone
	 * @param phone
	 */
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    /**
	 * <b>Summary:获取手机</b>
	 * getPhone
	 * @return String
	 */
    public String getPhone() {
    	return phone;
    }
	/**
	 * <b>Summary:设置电话</b>
	 * setTelephone
	 * @param telephone
	 */
    public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
    
    /**
	 * <b>Summary:获取电话</b>
	 * getTelephone
	 * @return String
	 */
    public String getTelephone() {
    	return telephone;
    }

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

}