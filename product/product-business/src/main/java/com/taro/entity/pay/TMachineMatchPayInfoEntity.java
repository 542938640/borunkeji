package com.taro.entity.pay;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;
import java.sql.Date;

/**
 *<p>Title:TMachineMatchPayInfoEntity.java</p>
 *<p>Description: Entity(对应表名:tmachinematchpayinfo)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:56:19
 */
public class TMachineMatchPayInfoEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 
    private String mmpimid;
	// 
    private Integer mmpisstid;
	// 
    private String mmpicreateuser;
	// 
    private Date mmpitime;

	/**
	 * <b>Summary:设置</b>
	 * setMmpimid
	 * @param mmpimid
	 */
    public void setMmpimid(String mmpimid) {
    	this.mmpimid = mmpimid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMmpimid
	 * @return String
	 */
    public String getMmpimid() {
    	return mmpimid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMmpisstid
	 * @param mmpisstid
	 */
    public void setMmpisstid(Integer mmpisstid) {
    	this.mmpisstid = mmpisstid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMmpisstid
	 * @return Integer
	 */
    public Integer getMmpisstid() {
    	return mmpisstid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMmpicreateuser
	 * @param mmpicreateuser
	 */
    public void setMmpicreateuser(String mmpicreateuser) {
    	this.mmpicreateuser = mmpicreateuser;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMmpicreateuser
	 * @return String
	 */
    public String getMmpicreateuser() {
    	return mmpicreateuser;
    }
	/**
	 * <b>Summary:设置</b>
	 * setMmpitime
	 * @param mmpitime
	 */
    public void setMmpitime(Date mmpitime) {
    	this.mmpitime = mmpitime;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getMmpitime
	 * @return Date
	 */
    public Date getMmpitime() {
    	return mmpitime;
    }

}