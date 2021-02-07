package com.taro.entity.advert;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:AdvertEndDetailEntity.java</p>
 *<p>Description: Entity(对应表名:t_advert_end_detail)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-04 10:13:50
 */
public class AdvertEndDetailEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 操作结束id
    private String end_pid;
	// 租户id
    private String tenants_pid;
	// 设备id
    private String device_pid;
	// 设备Did
    private String device_did;
	// 活动类型
    private String advert_class;
	// 结束语
    private String conclusion;
	// 
    private String c1;
	// 
    private String c2;
	// 
    private String c3;

	/**
	 * <b>Summary:设置操作结束id</b>
	 * setEnd_pid
	 * @param end_pid
	 */
    public void setEnd_pid(String end_pid) {
    	this.end_pid = end_pid;
    }
    
    /**
	 * <b>Summary:获取操作结束id</b>
	 * getEnd_pid
	 * @return String
	 */
    public String getEnd_pid() {
    	return end_pid;
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
	 * <b>Summary:设置设备Did</b>
	 * setDevice_did
	 * @param device_did
	 */
    public void setDevice_did(String device_did) {
    	this.device_did = device_did;
    }
    
    /**
	 * <b>Summary:获取设备Did</b>
	 * getDevice_did
	 * @return String
	 */
    public String getDevice_did() {
    	return device_did;
    }
	/**
	 * <b>Summary:设置活动类型</b>
	 * setAdvert_class
	 * @param advert_class
	 */
    public void setAdvert_class(String advert_class) {
    	this.advert_class = advert_class;
    }
    
    /**
	 * <b>Summary:获取活动类型</b>
	 * getAdvert_class
	 * @return String
	 */
    public String getAdvert_class() {
    	return advert_class;
    }
	/**
	 * <b>Summary:设置结束语</b>
	 * setConclusion
	 * @param conclusion
	 */
    public void setConclusion(String conclusion) {
    	this.conclusion = conclusion;
    }
    
    /**
	 * <b>Summary:获取结束语</b>
	 * getConclusion
	 * @return String
	 */
    public String getConclusion() {
    	return conclusion;
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

}