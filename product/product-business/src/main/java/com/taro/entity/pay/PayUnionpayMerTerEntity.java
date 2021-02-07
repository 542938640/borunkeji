package com.taro.entity.pay;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PayUnionpayMerTerEntity.java</p>
 *<p>Description:银联商户终端号 Entity(对应表名:t_pay_unionpay_mer_ter)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:16:04
 */
public class PayUnionpayMerTerEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 支付组id
    private String unionpay_pid;
	// 商户号id
    private String unionpay_mer_pid;
	// 租户id
    private String tenants_pid;
	// 终端号
    private String ter_number;

	/**
	 * <b>Summary:设置支付组id</b>
	 * setUnionpay_pid
	 * @param unionpay_pid
	 */
    public void setUnionpay_pid(String unionpay_pid) {
    	this.unionpay_pid = unionpay_pid;
    }
    
    /**
	 * <b>Summary:获取支付组id</b>
	 * getUnionpay_pid
	 * @return String
	 */
    public String getUnionpay_pid() {
    	return unionpay_pid;
    }
	/**
	 * <b>Summary:设置商户号id</b>
	 * setUnionpay_mer_pid
	 * @param unionpay_mer_pid
	 */
    public void setUnionpay_mer_pid(String unionpay_mer_pid) {
    	this.unionpay_mer_pid = unionpay_mer_pid;
    }
    
    /**
	 * <b>Summary:获取商户号id</b>
	 * getUnionpay_mer_pid
	 * @return String
	 */
    public String getUnionpay_mer_pid() {
    	return unionpay_mer_pid;
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
	 * <b>Summary:设置终端号</b>
	 * setTer_number
	 * @param ter_number
	 */
    public void setTer_number(String ter_number) {
    	this.ter_number = ter_number;
    }
    
    /**
	 * <b>Summary:获取终端号</b>
	 * getTer_number
	 * @return String
	 */
    public String getTer_number() {
    	return ter_number;
    }

}