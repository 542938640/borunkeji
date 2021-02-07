package com.taro.entity.device;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:DeviceTenantsEntity.java</p>
 *<p>Description:设备历史机构 Entity(对应表名:t_device_tenants)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-10 16:48:16
 */
public class DeviceTenantsEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 设备id
    private String device_pid;
	// 设备did
    private String device_did;
	// 总行id
    private String head_office_pid;
	// 省行id
    private String provincial_pid;
	// 市行id
    private String city_pid;
	// 支行id
    private String branch_pid;
	// 网点id
    private String outlets_pid;
	// 总行名称
    private String head_office_name;
	// 省行名称
    private String provincial_name;
	// 市行名称
    private String city_name;
	// 支行名称
    private String branch_name;
	// 网点名称
    private String outlets_name;
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
	 * <b>Summary:设置总行id</b>
	 * setHead_office_pid
	 * @param head_office_pid
	 */
    public void setHead_office_pid(String head_office_pid) {
    	this.head_office_pid = head_office_pid;
    }
    
    /**
	 * <b>Summary:获取总行id</b>
	 * getHead_office_pid
	 * @return String
	 */
    public String getHead_office_pid() {
    	return head_office_pid;
    }
	/**
	 * <b>Summary:设置省行id</b>
	 * setProvincial_pid
	 * @param provincial_pid
	 */
    public void setProvincial_pid(String provincial_pid) {
    	this.provincial_pid = provincial_pid;
    }
    
    /**
	 * <b>Summary:获取省行id</b>
	 * getProvincial_pid
	 * @return String
	 */
    public String getProvincial_pid() {
    	return provincial_pid;
    }
	/**
	 * <b>Summary:设置市行id</b>
	 * setCity_pid
	 * @param city_pid
	 */
    public void setCity_pid(String city_pid) {
    	this.city_pid = city_pid;
    }
    
    /**
	 * <b>Summary:获取市行id</b>
	 * getCity_pid
	 * @return String
	 */
    public String getCity_pid() {
    	return city_pid;
    }
	/**
	 * <b>Summary:设置支行id</b>
	 * setBranch_pid
	 * @param branch_pid
	 */
    public void setBranch_pid(String branch_pid) {
    	this.branch_pid = branch_pid;
    }
    
    /**
	 * <b>Summary:获取支行id</b>
	 * getBranch_pid
	 * @return String
	 */
    public String getBranch_pid() {
    	return branch_pid;
    }
	/**
	 * <b>Summary:设置网点id</b>
	 * setOutlets_pid
	 * @param outlets_pid
	 */
    public void setOutlets_pid(String outlets_pid) {
    	this.outlets_pid = outlets_pid;
    }
    
    /**
	 * <b>Summary:获取网点id</b>
	 * getOutlets_pid
	 * @return String
	 */
    public String getOutlets_pid() {
    	return outlets_pid;
    }
	/**
	 * <b>Summary:设置总行名称</b>
	 * setHead_office_name
	 * @param head_office_name
	 */
    public void setHead_office_name(String head_office_name) {
    	this.head_office_name = head_office_name;
    }
    
    /**
	 * <b>Summary:获取总行名称</b>
	 * getHead_office_name
	 * @return String
	 */
    public String getHead_office_name() {
    	return head_office_name;
    }
	/**
	 * <b>Summary:设置省行名称</b>
	 * setProvincial_name
	 * @param provincial_name
	 */
    public void setProvincial_name(String provincial_name) {
    	this.provincial_name = provincial_name;
    }
    
    /**
	 * <b>Summary:获取省行名称</b>
	 * getProvincial_name
	 * @return String
	 */
    public String getProvincial_name() {
    	return provincial_name;
    }
	/**
	 * <b>Summary:设置市行名称</b>
	 * setCity_name
	 * @param city_name
	 */
    public void setCity_name(String city_name) {
    	this.city_name = city_name;
    }
    
    /**
	 * <b>Summary:获取市行名称</b>
	 * getCity_name
	 * @return String
	 */
    public String getCity_name() {
    	return city_name;
    }
	/**
	 * <b>Summary:设置支行名称</b>
	 * setBranch_name
	 * @param branch_name
	 */
    public void setBranch_name(String branch_name) {
    	this.branch_name = branch_name;
    }
    
    /**
	 * <b>Summary:获取支行名称</b>
	 * getBranch_name
	 * @return String
	 */
    public String getBranch_name() {
    	return branch_name;
    }
	/**
	 * <b>Summary:设置网点名称</b>
	 * setOutlets_name
	 * @param outlets_name
	 */
    public void setOutlets_name(String outlets_name) {
    	this.outlets_name = outlets_name;
    }
    
    /**
	 * <b>Summary:获取网点名称</b>
	 * getOutlets_name
	 * @return String
	 */
    public String getOutlets_name() {
    	return outlets_name;
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