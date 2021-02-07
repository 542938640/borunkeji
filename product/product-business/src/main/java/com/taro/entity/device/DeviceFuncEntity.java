package com.taro.entity.device;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:DeviceFuncEntity.java</p>
 *<p>Description: Entity(对应表名:t_device_func)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-16 19:41:25
 */
public class DeviceFuncEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 设备id
    private String device_pid;
	// 设备did
    private String device_did;
	// 是否启用手机号
    private String is_phohe;
	// 是否启用用户签名
    private String is_usersign;
	// 是否启用用户姓名
    private String is_username;
	// 是否启用卡类型
    private String is_cardtype;
	// 是否启用卡号后六位
    private String is_cardnumber;
	// 是否启用业务类型
    private String is_busitype;
	// 是否启用备注
    private String is_remark;
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
	// 
    private String c6;
	// 
    private String c7;
	// 
    private String c8;
	// 
    private String c9;
	// 
    private String c10;

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
	 * <b>Summary:设置是否启用手机号</b>
	 * setIs_phohe
	 * @param is_phohe
	 */
    public void setIs_phohe(String is_phohe) {
    	this.is_phohe = is_phohe;
    }
    
    /**
	 * <b>Summary:获取是否启用手机号</b>
	 * getIs_phohe
	 * @return String
	 */
    public String getIs_phohe() {
    	return is_phohe;
    }
	/**
	 * <b>Summary:设置是否启用用户签名</b>
	 * setIs_usersign
	 * @param is_usersign
	 */
    public void setIs_usersign(String is_usersign) {
    	this.is_usersign = is_usersign;
    }
    
    /**
	 * <b>Summary:获取是否启用用户签名</b>
	 * getIs_usersign
	 * @return String
	 */
    public String getIs_usersign() {
    	return is_usersign;
    }
	/**
	 * <b>Summary:设置是否启用用户姓名</b>
	 * setIs_username
	 * @param is_username
	 */
    public void setIs_username(String is_username) {
    	this.is_username = is_username;
    }
    
    /**
	 * <b>Summary:获取是否启用用户姓名</b>
	 * getIs_username
	 * @return String
	 */
    public String getIs_username() {
    	return is_username;
    }
	/**
	 * <b>Summary:设置是否启用卡类型</b>
	 * setIs_cardtype
	 * @param is_cardtype
	 */
    public void setIs_cardtype(String is_cardtype) {
    	this.is_cardtype = is_cardtype;
    }
    
    /**
	 * <b>Summary:获取是否启用卡类型</b>
	 * getIs_cardtype
	 * @return String
	 */
    public String getIs_cardtype() {
    	return is_cardtype;
    }
	/**
	 * <b>Summary:设置是否启用卡号后六位</b>
	 * setIs_cardnumber
	 * @param is_cardnumber
	 */
    public void setIs_cardnumber(String is_cardnumber) {
    	this.is_cardnumber = is_cardnumber;
    }
    
    /**
	 * <b>Summary:获取是否启用卡号后六位</b>
	 * getIs_cardnumber
	 * @return String
	 */
    public String getIs_cardnumber() {
    	return is_cardnumber;
    }
	/**
	 * <b>Summary:设置是否启用业务类型</b>
	 * setIs_busitype
	 * @param is_busitype
	 */
    public void setIs_busitype(String is_busitype) {
    	this.is_busitype = is_busitype;
    }
    
    /**
	 * <b>Summary:获取是否启用业务类型</b>
	 * getIs_busitype
	 * @return String
	 */
    public String getIs_busitype() {
    	return is_busitype;
    }
	/**
	 * <b>Summary:设置是否启用备注</b>
	 * setIs_remark
	 * @param is_remark
	 */
    public void setIs_remark(String is_remark) {
    	this.is_remark = is_remark;
    }
    
    /**
	 * <b>Summary:获取是否启用备注</b>
	 * getIs_remark
	 * @return String
	 */
    public String getIs_remark() {
    	return is_remark;
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
	/**
	 * <b>Summary:设置</b>
	 * setC6
	 * @param c6
	 */
    public void setC6(String c6) {
    	this.c6 = c6;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC6
	 * @return String
	 */
    public String getC6() {
    	return c6;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC7
	 * @param c7
	 */
    public void setC7(String c7) {
    	this.c7 = c7;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC7
	 * @return String
	 */
    public String getC7() {
    	return c7;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC8
	 * @param c8
	 */
    public void setC8(String c8) {
    	this.c8 = c8;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC8
	 * @return String
	 */
    public String getC8() {
    	return c8;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC9
	 * @param c9
	 */
    public void setC9(String c9) {
    	this.c9 = c9;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC9
	 * @return String
	 */
    public String getC9() {
    	return c9;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC10
	 * @param c10
	 */
    public void setC10(String c10) {
    	this.c10 = c10;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC10
	 * @return String
	 */
    public String getC10() {
    	return c10;
    }

}