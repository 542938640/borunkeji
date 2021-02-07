package com.taro.entity.pay;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;
import java.sql.Date;

/**
 *<p>Title:TPaySettingEntity.java</p>
 *<p>Description: Entity(对应表名:tpaysetting)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:55:56
 */
public class TPaySettingEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 
    private String wxmch_id;
	// 
    private String wxsubmch_id;
	// 
    private String wxappid;
	// 
    private String wxsubappid;
	// 
    private String wxappkey;
	// 
    private String appsecret;
	// 
    private String wxapi_cert;
	// 
    private String alipid;
	// 
    private String alipublickkey;
	// 
    private String aliprivatekey;
	// 
    private String alitoken;
	// 
    private String aliseller_email;
	// 
    private String alitransinalipartner;
	// 
    private String alikey;
	// 
    private Integer wxmode;
	// 
    private Integer alimode;
	// 
    private Integer wxenabled;
	// 
    private Integer alienabled;
	// 
    private String psservicetel;
	// 
    private String pscreateuser;
	// 
    private Date pscreatetime;
	// 
    private String pscreatecompany;
	// 
    private String subappsecret;
	// 
    private String isprofitshare;

	/**
	 * <b>Summary:设置</b>
	 * setWxmch_id
	 * @param wxmch_id
	 */
    public void setWxmch_id(String wxmch_id) {
    	this.wxmch_id = wxmch_id;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxmch_id
	 * @return String
	 */
    public String getWxmch_id() {
    	return wxmch_id;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxsubmch_id
	 * @param wxsubmch_id
	 */
    public void setWxsubmch_id(String wxsubmch_id) {
    	this.wxsubmch_id = wxsubmch_id;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxsubmch_id
	 * @return String
	 */
    public String getWxsubmch_id() {
    	return wxsubmch_id;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxappid
	 * @param wxappid
	 */
    public void setWxappid(String wxappid) {
    	this.wxappid = wxappid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxappid
	 * @return String
	 */
    public String getWxappid() {
    	return wxappid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxsubappid
	 * @param wxsubappid
	 */
    public void setWxsubappid(String wxsubappid) {
    	this.wxsubappid = wxsubappid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxsubappid
	 * @return String
	 */
    public String getWxsubappid() {
    	return wxsubappid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxappkey
	 * @param wxappkey
	 */
    public void setWxappkey(String wxappkey) {
    	this.wxappkey = wxappkey;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxappkey
	 * @return String
	 */
    public String getWxappkey() {
    	return wxappkey;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAppsecret
	 * @param appsecret
	 */
    public void setAppsecret(String appsecret) {
    	this.appsecret = appsecret;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAppsecret
	 * @return String
	 */
    public String getAppsecret() {
    	return appsecret;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxapi_cert
	 * @param wxapi_cert
	 */
    public void setWxapi_cert(String wxapi_cert) {
    	this.wxapi_cert = wxapi_cert;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxapi_cert
	 * @return String
	 */
    public String getWxapi_cert() {
    	return wxapi_cert;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlipid
	 * @param alipid
	 */
    public void setAlipid(String alipid) {
    	this.alipid = alipid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlipid
	 * @return String
	 */
    public String getAlipid() {
    	return alipid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlipublickkey
	 * @param alipublickkey
	 */
    public void setAlipublickkey(String alipublickkey) {
    	this.alipublickkey = alipublickkey;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlipublickkey
	 * @return String
	 */
    public String getAlipublickkey() {
    	return alipublickkey;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAliprivatekey
	 * @param aliprivatekey
	 */
    public void setAliprivatekey(String aliprivatekey) {
    	this.aliprivatekey = aliprivatekey;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAliprivatekey
	 * @return String
	 */
    public String getAliprivatekey() {
    	return aliprivatekey;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlitoken
	 * @param alitoken
	 */
    public void setAlitoken(String alitoken) {
    	this.alitoken = alitoken;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlitoken
	 * @return String
	 */
    public String getAlitoken() {
    	return alitoken;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAliseller_email
	 * @param aliseller_email
	 */
    public void setAliseller_email(String aliseller_email) {
    	this.aliseller_email = aliseller_email;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAliseller_email
	 * @return String
	 */
    public String getAliseller_email() {
    	return aliseller_email;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlitransinalipartner
	 * @param alitransinalipartner
	 */
    public void setAlitransinalipartner(String alitransinalipartner) {
    	this.alitransinalipartner = alitransinalipartner;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlitransinalipartner
	 * @return String
	 */
    public String getAlitransinalipartner() {
    	return alitransinalipartner;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlikey
	 * @param alikey
	 */
    public void setAlikey(String alikey) {
    	this.alikey = alikey;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlikey
	 * @return String
	 */
    public String getAlikey() {
    	return alikey;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxmode
	 * @param wxmode
	 */
    public void setWxmode(Integer wxmode) {
    	this.wxmode = wxmode;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxmode
	 * @return Integer
	 */
    public Integer getWxmode() {
    	return wxmode;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlimode
	 * @param alimode
	 */
    public void setAlimode(Integer alimode) {
    	this.alimode = alimode;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlimode
	 * @return Integer
	 */
    public Integer getAlimode() {
    	return alimode;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxenabled
	 * @param wxenabled
	 */
    public void setWxenabled(Integer wxenabled) {
    	this.wxenabled = wxenabled;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxenabled
	 * @return Integer
	 */
    public Integer getWxenabled() {
    	return wxenabled;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlienabled
	 * @param alienabled
	 */
    public void setAlienabled(Integer alienabled) {
    	this.alienabled = alienabled;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlienabled
	 * @return Integer
	 */
    public Integer getAlienabled() {
    	return alienabled;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPsservicetel
	 * @param psservicetel
	 */
    public void setPsservicetel(String psservicetel) {
    	this.psservicetel = psservicetel;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPsservicetel
	 * @return String
	 */
    public String getPsservicetel() {
    	return psservicetel;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPscreateuser
	 * @param pscreateuser
	 */
    public void setPscreateuser(String pscreateuser) {
    	this.pscreateuser = pscreateuser;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPscreateuser
	 * @return String
	 */
    public String getPscreateuser() {
    	return pscreateuser;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPscreatetime
	 * @param pscreatetime
	 */
    public void setPscreatetime(Date pscreatetime) {
    	this.pscreatetime = pscreatetime;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPscreatetime
	 * @return Date
	 */
    public Date getPscreatetime() {
    	return pscreatetime;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPscreatecompany
	 * @param pscreatecompany
	 */
    public void setPscreatecompany(String pscreatecompany) {
    	this.pscreatecompany = pscreatecompany;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPscreatecompany
	 * @return String
	 */
    public String getPscreatecompany() {
    	return pscreatecompany;
    }
	/**
	 * <b>Summary:设置</b>
	 * setSubappsecret
	 * @param subappsecret
	 */
    public void setSubappsecret(String subappsecret) {
    	this.subappsecret = subappsecret;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getSubappsecret
	 * @return String
	 */
    public String getSubappsecret() {
    	return subappsecret;
    }
	/**
	 * <b>Summary:设置</b>
	 * setIsprofitshare
	 * @param isprofitshare
	 */
    public void setIsprofitshare(String isprofitshare) {
    	this.isprofitshare = isprofitshare;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getIsprofitshare
	 * @return String
	 */
    public String getIsprofitshare() {
    	return isprofitshare;
    }

}