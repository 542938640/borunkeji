package com.taro.entity.pay;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PayBaoxinEntity.java</p>
 *<p>Description: Entity(对应表名:t_pay_baoxin)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 23:23:38
 */
public class PayBaoxinEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 名称
    private String name;
	// 微信MchId
    private String wx_mchid;
	// 微信SubMchId
    private String wx_sub_mchid;
	// 微信AppId
    private String wx_appid;
	// 微信SubAppId
    private String wx_sup_appid;
	// 微信AppSecret
    private String wx_appsecret;
	// 微信AppKey
    private String wx_appkey;
	// 支付宝Pid
    private String zfb_pid;
	// 微信SubAppSecret
    private String wx_sup_appsecret;

	// 租户id
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
	 * <b>Summary:设置微信MchId</b>
	 * setWx_mchid
	 * @param wx_mchid
	 */
    public void setWx_mchid(String wx_mchid) {
    	this.wx_mchid = wx_mchid;
    }
    
    /**
	 * <b>Summary:获取微信MchId</b>
	 * getWx_mchid
	 * @return String
	 */
    public String getWx_mchid() {
    	return wx_mchid;
    }
	/**
	 * <b>Summary:设置微信SubMchId</b>
	 * setWx_sub_mchid
	 * @param wx_sub_mchid
	 */
    public void setWx_sub_mchid(String wx_sub_mchid) {
    	this.wx_sub_mchid = wx_sub_mchid;
    }
    
    /**
	 * <b>Summary:获取微信SubMchId</b>
	 * getWx_sub_mchid
	 * @return String
	 */
    public String getWx_sub_mchid() {
    	return wx_sub_mchid;
    }
	/**
	 * <b>Summary:设置微信AppId</b>
	 * setWx_appid
	 * @param wx_appid
	 */
    public void setWx_appid(String wx_appid) {
    	this.wx_appid = wx_appid;
    }
    
    /**
	 * <b>Summary:获取微信AppId</b>
	 * getWx_appid
	 * @return String
	 */
    public String getWx_appid() {
    	return wx_appid;
    }
	/**
	 * <b>Summary:设置微信SubAppId</b>
	 * setWx_sup_appid
	 * @param wx_sup_appid
	 */
    public void setWx_sup_appid(String wx_sup_appid) {
    	this.wx_sup_appid = wx_sup_appid;
    }
    
    /**
	 * <b>Summary:获取微信SubAppId</b>
	 * getWx_sup_appid
	 * @return String
	 */
    public String getWx_sup_appid() {
    	return wx_sup_appid;
    }
	/**
	 * <b>Summary:设置微信AppSecret</b>
	 * setWx_appsecret
	 * @param wx_appsecret
	 */
    public void setWx_appsecret(String wx_appsecret) {
    	this.wx_appsecret = wx_appsecret;
    }
    
    /**
	 * <b>Summary:获取微信AppSecret</b>
	 * getWx_appsecret
	 * @return String
	 */
    public String getWx_appsecret() {
    	return wx_appsecret;
    }
	/**
	 * <b>Summary:设置微信AppKey</b>
	 * setWx_appkey
	 * @param wx_appkey
	 */
    public void setWx_appkey(String wx_appkey) {
    	this.wx_appkey = wx_appkey;
    }
    
    /**
	 * <b>Summary:获取微信AppKey</b>
	 * getWx_appkey
	 * @return String
	 */
    public String getWx_appkey() {
    	return wx_appkey;
    }
	/**
	 * <b>Summary:设置支付宝Pid</b>
	 * setZfb_pid
	 * @param zfb_pid
	 */
    public void setZfb_pid(String zfb_pid) {
    	this.zfb_pid = zfb_pid;
    }
    
    /**
	 * <b>Summary:获取支付宝Pid</b>
	 * getZfb_pid
	 * @return String
	 */
    public String getZfb_pid() {
    	return zfb_pid;
    }
	/**
	 * <b>Summary:设置微信SubAppSecret</b>
	 * setWx_sup_appsecret
	 * @param wx_sup_appsecret
	 */
    public void setWx_sup_appsecret(String wx_sup_appsecret) {
    	this.wx_sup_appsecret = wx_sup_appsecret;
    }
    
    /**
	 * <b>Summary:获取微信SubAppSecret</b>
	 * getWx_sup_appsecret
	 * @return String
	 */
    public String getWx_sup_appsecret() {
    	return wx_sup_appsecret;
    }

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

}