package com.taro.entity.finance;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:FinanceNoteDetailEntity.java</p>
 *<p>Description: Entity(对应表名:t_finance_note_detail)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-02-05 13:48:14
 */
public class FinanceNoteDetailEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 业务类型(1=验证码, 2=领劵吧)
    private String busi_type;
	// 业务名称
    private String busi_name;
	// 
    private Integer num;
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

	// 租户名称
    private String tenants_name;
    
	/**
	 * <b>Summary:设置业务类型</b>
	 * setBusi_type
	 * @param busi_type
	 */
    public void setBusi_type(String busi_type) {
    	this.busi_type = busi_type;
    }
    
    /**
	 * <b>Summary:获取业务类型</b>
	 * getBusi_type
	 * @return String
	 */
    public String getBusi_type() {
    	return busi_type;
    }
	/**
	 * <b>Summary:设置业务名称</b>
	 * setBusi_name
	 * @param busi_name
	 */
    public void setBusi_name(String busi_name) {
    	this.busi_name = busi_name;
    }
    
    /**
	 * <b>Summary:获取业务名称</b>
	 * getBusi_name
	 * @return String
	 */
    public String getBusi_name() {
    	return busi_name;
    }
	/**
	 * <b>Summary:设置</b>
	 * setNum
	 * @param num
	 */
    public void setNum(Integer num) {
    	this.num = num;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getNum
	 * @return Integer
	 */
    public Integer getNum() {
    	return num;
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

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

}