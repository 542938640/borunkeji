package com.taro.entity.pay;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PayUnionpayEntity.java</p>
 *<p>Description:银联支付组 Entity(对应表名:t_pay_unionpay)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:33
 */
public class PayUnionpayEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 租户id
    private String tenants_pid;
	// 名称
    private String name;

	// 租户名称
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

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

}