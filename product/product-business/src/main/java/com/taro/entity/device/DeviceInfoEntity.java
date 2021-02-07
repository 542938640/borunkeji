package com.taro.entity.device;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:DeviceInfoEntity.java</p>
 *<p>Description: Entity(对应表名:TDEVICEINFO)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-17 11:47:03
 */
public class DeviceInfoEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// 
    private String did;
	// 
    private Integer advertisingswitch;
	// 
    private Integer alicloudcode;
	// 
    private String city;
	// 
    private String country;
	// 
    private Integer enable;
	// 
    private String diaddress;
	// 
    private String dialias;
	// 
    private String diassetsid;
	// 
    private String dibelonguser;
	// 
    private String dibusinessid;
	// 
    private Date dibuytime;
	// 
    private String digroup;
	// 
    private String diinsidetemp;
	// 
    private Double dillatitude;
	// 
    private Double dilongitude;
	// 
    private Integer dinoline;
	// 
    private String dipwd;
	// 
    private String disceneid;
	// 
    private Integer disize;
	// 
    private Integer diwork;
	// 
    private String internaladdress;
	// 
    private Integer iscart;
	// 
    private Double maxtemperature;
	// 
    private Integer mibuyprice;
	// 
    private Integer micclimit;
	// 
    private Integer micvs;
	// 
    private Integer miisdeploy;
	// 
    private Double mintemperature;
	// 
    private Integer miout;
	// 
    private String miouter_temp;
	// 
    private String mipoweroff;
	// 
    private Integer miqrctype;
	// 
    private Integer miserialnumber;
	// 
    private Integer misizetype;
	// 
    private String posturl;
	// 
    private String province;
	// 
    private Integer pureelectron;
	// 
    private Integer refundcount;
	// 
    private Integer refundverify;
	// 
    private Integer scheduledstate;
	// 
    private String scheduledtime;
	// 
    private Integer spellluck;
	// 
    private String threetiercity;
	// 
    private String version;
	// 
    private Integer wxsinglediscount;

    // 在线数量
    private Integer dinoline_num;

    // 分支机构数量
    private Long org_num;
    
    private List<DeviceInfoEntity> dinolineList;
    
	/**
	 * <b>Summary:设置</b>
	 * setDid
	 * @param did
	 */
    public void setDid(String did) {
    	this.did = did;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDid
	 * @return String
	 */
    public String getDid() {
    	return did;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAdvertisingswitch
	 * @param advertisingswitch
	 */
    public void setAdvertisingswitch(Integer advertisingswitch) {
    	this.advertisingswitch = advertisingswitch;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAdvertisingswitch
	 * @return Integer
	 */
    public Integer getAdvertisingswitch() {
    	return advertisingswitch;
    }
	/**
	 * <b>Summary:设置</b>
	 * setAlicloudcode
	 * @param alicloudcode
	 */
    public void setAlicloudcode(Integer alicloudcode) {
    	this.alicloudcode = alicloudcode;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getAlicloudcode
	 * @return Integer
	 */
    public Integer getAlicloudcode() {
    	return alicloudcode;
    }
	/**
	 * <b>Summary:设置</b>
	 * setCity
	 * @param city
	 */
    public void setCity(String city) {
    	this.city = city;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getCity
	 * @return String
	 */
    public String getCity() {
    	return city;
    }
	/**
	 * <b>Summary:设置</b>
	 * setCountry
	 * @param country
	 */
    public void setCountry(String country) {
    	this.country = country;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getCountry
	 * @return String
	 */
    public String getCountry() {
    	return country;
    }
	/**
	 * <b>Summary:设置</b>
	 * setEnable
	 * @param enable
	 */
    public void setEnable(Integer enable) {
    	this.enable = enable;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getEnable
	 * @return Integer
	 */
    public Integer getEnable() {
    	return enable;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDiaddress
	 * @param diaddress
	 */
    public void setDiaddress(String diaddress) {
    	this.diaddress = diaddress;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDiaddress
	 * @return String
	 */
    public String getDiaddress() {
    	return diaddress;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDialias
	 * @param dialias
	 */
    public void setDialias(String dialias) {
    	this.dialias = dialias;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDialias
	 * @return String
	 */
    public String getDialias() {
    	return dialias;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDiassetsid
	 * @param diassetsid
	 */
    public void setDiassetsid(String diassetsid) {
    	this.diassetsid = diassetsid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDiassetsid
	 * @return String
	 */
    public String getDiassetsid() {
    	return diassetsid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDibelonguser
	 * @param dibelonguser
	 */
    public void setDibelonguser(String dibelonguser) {
    	this.dibelonguser = dibelonguser;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDibelonguser
	 * @return String
	 */
    public String getDibelonguser() {
    	return dibelonguser;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDibusinessid
	 * @param dibusinessid
	 */
    public void setDibusinessid(String dibusinessid) {
    	this.dibusinessid = dibusinessid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDibusinessid
	 * @return String
	 */
    public String getDibusinessid() {
    	return dibusinessid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDibuytime
	 * @param dibuytime
	 */
    public void setDibuytime(Date dibuytime) {
    	this.dibuytime = dibuytime;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDibuytime
	 * @return Date
	 */
    public Date getDibuytime() {
    	return dibuytime;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDigroup
	 * @param digroup
	 */
    public void setDigroup(String digroup) {
    	this.digroup = digroup;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDigroup
	 * @return String
	 */
    public String getDigroup() {
    	return digroup;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDiinsidetemp
	 * @param diinsidetemp
	 */
    public void setDiinsidetemp(String diinsidetemp) {
    	this.diinsidetemp = diinsidetemp;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDiinsidetemp
	 * @return String
	 */
    public String getDiinsidetemp() {
    	return diinsidetemp;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDillatitude
	 * @param dillatitude
	 */
    public void setDillatitude(Double dillatitude) {
    	this.dillatitude = dillatitude;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDillatitude
	 * @return Double
	 */
    public Double getDillatitude() {
    	return dillatitude;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDilongitude
	 * @param dilongitude
	 */
    public void setDilongitude(Double dilongitude) {
    	this.dilongitude = dilongitude;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDilongitude
	 * @return Double
	 */
    public Double getDilongitude() {
    	return dilongitude;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDinoline
	 * @param dinoline
	 */
    public void setDinoline(Integer dinoline) {
    	this.dinoline = dinoline;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDinoline
	 * @return Integer
	 */
    public Integer getDinoline() {
    	return dinoline;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDipwd
	 * @param dipwd
	 */
    public void setDipwd(String dipwd) {
    	this.dipwd = dipwd;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDipwd
	 * @return String
	 */
    public String getDipwd() {
    	return dipwd;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDisceneid
	 * @param disceneid
	 */
    public void setDisceneid(String disceneid) {
    	this.disceneid = disceneid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDisceneid
	 * @return String
	 */
    public String getDisceneid() {
    	return disceneid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDisize
	 * @param disize
	 */
    public void setDisize(Integer disize) {
    	this.disize = disize;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDisize
	 * @return Integer
	 */
    public Integer getDisize() {
    	return disize;
    }
	/**
	 * <b>Summary:设置</b>
	 * setDiwork
	 * @param diwork
	 */
    public void setDiwork(Integer diwork) {
    	this.diwork = diwork;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getDiwork
	 * @return Integer
	 */
    public Integer getDiwork() {
    	return diwork;
    }
	/**
	 * <b>Summary:设置</b>
	 * setInternaladdress
	 * @param internaladdress
	 */
    public void setInternaladdress(String internaladdress) {
    	this.internaladdress = internaladdress;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getInternaladdress
	 * @return String
	 */
    public String getInternaladdress() {
    	return internaladdress;
    }
	/**
	 * <b>Summary:设置</b>
	 * setIscart
	 * @param iscart
	 */
    public void setIscart(Integer iscart) {
    	this.iscart = iscart;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getIscart
	 * @return Integer
	 */
    public Integer getIscart() {
    	return iscart;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMaxtemperature
	 * @param maxtemperature
	 */
    public void setMaxtemperature(Double maxtemperature) {
    	this.maxtemperature = maxtemperature;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMaxtemperature
	 * @return Double
	 */
    public Double getMaxtemperature() {
    	return maxtemperature;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMibuyprice
	 * @param mibuyprice
	 */
    public void setMibuyprice(Integer mibuyprice) {
    	this.mibuyprice = mibuyprice;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMibuyprice
	 * @return Integer
	 */
    public Integer getMibuyprice() {
    	return mibuyprice;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMicclimit
	 * @param micclimit
	 */
    public void setMicclimit(Integer micclimit) {
    	this.micclimit = micclimit;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMicclimit
	 * @return Integer
	 */
    public Integer getMicclimit() {
    	return micclimit;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMicvs
	 * @param micvs
	 */
    public void setMicvs(Integer micvs) {
    	this.micvs = micvs;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMicvs
	 * @return Integer
	 */
    public Integer getMicvs() {
    	return micvs;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMiisdeploy
	 * @param miisdeploy
	 */
    public void setMiisdeploy(Integer miisdeploy) {
    	this.miisdeploy = miisdeploy;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMiisdeploy
	 * @return Integer
	 */
    public Integer getMiisdeploy() {
    	return miisdeploy;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMintemperature
	 * @param mintemperature
	 */
    public void setMintemperature(Double mintemperature) {
    	this.mintemperature = mintemperature;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMintemperature
	 * @return Double
	 */
    public Double getMintemperature() {
    	return mintemperature;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMiout
	 * @param miout
	 */
    public void setMiout(Integer miout) {
    	this.miout = miout;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMiout
	 * @return Integer
	 */
    public Integer getMiout() {
    	return miout;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMiouter_temp
	 * @param miouter_temp
	 */
    public void setMiouter_temp(String miouter_temp) {
    	this.miouter_temp = miouter_temp;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMiouter_temp
	 * @return String
	 */
    public String getMiouter_temp() {
    	return miouter_temp;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMipoweroff
	 * @param mipoweroff
	 */
    public void setMipoweroff(String mipoweroff) {
    	this.mipoweroff = mipoweroff;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMipoweroff
	 * @return String
	 */
    public String getMipoweroff() {
    	return mipoweroff;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMiqrctype
	 * @param miqrctype
	 */
    public void setMiqrctype(Integer miqrctype) {
    	this.miqrctype = miqrctype;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMiqrctype
	 * @return Integer
	 */
    public Integer getMiqrctype() {
    	return miqrctype;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMiserialnumber
	 * @param miserialnumber
	 */
    public void setMiserialnumber(Integer miserialnumber) {
    	this.miserialnumber = miserialnumber;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMiserialnumber
	 * @return Integer
	 */
    public Integer getMiserialnumber() {
    	return miserialnumber;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMisizetype
	 * @param misizetype
	 */
    public void setMisizetype(Integer misizetype) {
    	this.misizetype = misizetype;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMisizetype
	 * @return Integer
	 */
    public Integer getMisizetype() {
    	return misizetype;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPosturl
	 * @param posturl
	 */
    public void setPosturl(String posturl) {
    	this.posturl = posturl;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPosturl
	 * @return String
	 */
    public String getPosturl() {
    	return posturl;
    }
	/**
	 * <b>Summary:设置</b>
	 * setProvince
	 * @param province
	 */
    public void setProvince(String province) {
    	this.province = province;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getProvince
	 * @return String
	 */
    public String getProvince() {
    	return province;
    }
	/**
	 * <b>Summary:设置</b>
	 * setPureelectron
	 * @param pureelectron
	 */
    public void setPureelectron(Integer pureelectron) {
    	this.pureelectron = pureelectron;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getPureelectron
	 * @return Integer
	 */
    public Integer getPureelectron() {
    	return pureelectron;
    }
	/**
	 * <b>Summary:设置</b>
	 * setRefundcount
	 * @param refundcount
	 */
    public void setRefundcount(Integer refundcount) {
    	this.refundcount = refundcount;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getRefundcount
	 * @return Integer
	 */
    public Integer getRefundcount() {
    	return refundcount;
    }
	/**
	 * <b>Summary:设置</b>
	 * setRefundverify
	 * @param refundverify
	 */
    public void setRefundverify(Integer refundverify) {
    	this.refundverify = refundverify;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getRefundverify
	 * @return Integer
	 */
    public Integer getRefundverify() {
    	return refundverify;
    }
	/**
	 * <b>Summary:设置</b>
	 * setScheduledstate
	 * @param scheduledstate
	 */
    public void setScheduledstate(Integer scheduledstate) {
    	this.scheduledstate = scheduledstate;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getScheduledstate
	 * @return Integer
	 */
    public Integer getScheduledstate() {
    	return scheduledstate;
    }
	/**
	 * <b>Summary:设置</b>
	 * setScheduledtime
	 * @param scheduledtime
	 */
    public void setScheduledtime(String scheduledtime) {
    	this.scheduledtime = scheduledtime;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getScheduledtime
	 * @return String
	 */
    public String getScheduledtime() {
    	return scheduledtime;
    }
	/**
	 * <b>Summary:设置</b>
	 * setSpellluck
	 * @param spellluck
	 */
    public void setSpellluck(Integer spellluck) {
    	this.spellluck = spellluck;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getSpellluck
	 * @return Integer
	 */
    public Integer getSpellluck() {
    	return spellluck;
    }
	/**
	 * <b>Summary:设置</b>
	 * setThreetiercity
	 * @param threetiercity
	 */
    public void setThreetiercity(String threetiercity) {
    	this.threetiercity = threetiercity;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getThreetiercity
	 * @return String
	 */
    public String getThreetiercity() {
    	return threetiercity;
    }
	/**
	 * <b>Summary:设置</b>
	 * setVersion
	 * @param version
	 */
    public void setVersion(String version) {
    	this.version = version;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getVersion
	 * @return String
	 */
    public String getVersion() {
    	return version;
    }
	/**
	 * <b>Summary:设置</b>
	 * setWxsinglediscount
	 * @param wxsinglediscount
	 */
    public void setWxsinglediscount(Integer wxsinglediscount) {
    	this.wxsinglediscount = wxsinglediscount;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getWxsinglediscount
	 * @return Integer
	 */
    public Integer getWxsinglediscount() {
    	return wxsinglediscount;
    }

	public Integer getDinoline_num() {
		return dinoline_num;
	}

	public void setDinoline_num(Integer dinoline_num) {
		this.dinoline_num = dinoline_num;
	}

	public Long getOrg_num() {
		return org_num;
	}

	public void setOrg_num(Long org_num) {
		this.org_num = org_num;
	}

	public List<DeviceInfoEntity> getDinolineList() {
		return dinolineList;
	}

	public void setDinolineList(List<DeviceInfoEntity> dinolineList) {
		this.dinolineList = dinolineList;
	}

}