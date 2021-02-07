package com.taro.entity.device;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:DeviceBusitypeEntity.java</p>
 *<p>Description: Entity(对应表名:t_device_busitype)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-16 19:41:11
 */
public class DeviceBusitypeEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 设备id
    private String device_pid;
	// 设备did
    private String device_did;
	// 业务类型
    private String busitype;
	// 
    private String c1;
	// 
    private String c2;
	// 
    private String c3;
	// 
    private String c4;
	// 
    private String c5;

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
	 * <b>Summary:设置设备id</b>
	 * setDevice_pid
	 * @param device_pid
	 */
    public void setDevice_pid(String device_pid) {
    	this.device_pid = device_pid;
    }
    
    /**
	 * <b>Summary:获取设备id</b>
	 * getDevice_pid
	 * @return String
	 */
    public String getDevice_pid() {
    	return device_pid;
    }
	/**
	 * <b>Summary:设置设备did</b>
	 * setDevice_did
	 * @param device_did
	 */
    public void setDevice_did(String device_did) {
    	this.device_did = device_did;
    }
    
    /**
	 * <b>Summary:获取设备did</b>
	 * getDevice_did
	 * @return String
	 */
    public String getDevice_did() {
    	return device_did;
    }
	/**
	 * <b>Summary:设置业务类型</b>
	 * setBusitype
	 * @param busitype
	 */
    public void setBusitype(String busitype) {
    	this.busitype = busitype;
    }
    
    /**
	 * <b>Summary:获取业务类型</b>
	 * getBusitype
	 * @return String
	 */
    public String getBusitype() {
    	return busitype;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC1
	 * @param c1
	 */
    public void setC1(String c1) {
    	this.c1 = c1;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC1
	 * @return String
	 */
    public String getC1() {
    	return c1;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC2
	 * @param c2
	 */
    public void setC2(String c2) {
    	this.c2 = c2;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC2
	 * @return String
	 */
    public String getC2() {
    	return c2;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC3
	 * @param c3
	 */
    public void setC3(String c3) {
    	this.c3 = c3;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC3
	 * @return String
	 */
    public String getC3() {
    	return c3;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC4
	 * @param c4
	 */
    public void setC4(String c4) {
    	this.c4 = c4;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC4
	 * @return String
	 */
    public String getC4() {
    	return c4;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC5
	 * @param c5
	 */
    public void setC5(String c5) {
    	this.c5 = c5;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC5
	 * @return String
	 */
    public String getC5() {
    	return c5;
    }

}