package com.taro.entity.merchants;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:MerchantsCouponShareEntity.java</p>
 *<p>Description:商户优惠券共享 Entity(对应表名:t_merchants_coupon_share)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-12-17 19:22:13
 */
public class MerchantsCouponShareEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 优惠券id
    private String coupon_pid;
	// 共享网点
    private String share_pid;
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
	 * <b>Summary:设置优惠券id</b>
	 * setCoupon_pid
	 * @param coupon_pid
	 */
    public void setCoupon_pid(String coupon_pid) {
    	this.coupon_pid = coupon_pid;
    }
    
    /**
	 * <b>Summary:获取优惠券id</b>
	 * getCoupon_pid
	 * @return String
	 */
    public String getCoupon_pid() {
    	return coupon_pid;
    }
	/**
	 * <b>Summary:设置共享网点</b>
	 * setShare_pid
	 * @param share_pid
	 */
    public void setShare_pid(String share_pid) {
    	this.share_pid = share_pid;
    }
    
    /**
	 * <b>Summary:获取共享网点</b>
	 * getShare_pid
	 * @return String
	 */
    public String getShare_pid() {
    	return share_pid;
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