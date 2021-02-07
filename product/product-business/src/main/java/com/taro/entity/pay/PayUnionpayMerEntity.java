package com.taro.entity.pay;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PayUnionpayMerEntity.java</p>
 *<p>Description:银联商户号 Entity(对应表名:t_pay_unionpay_mer)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:52
 */
public class PayUnionpayMerEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 支付组id
    private String unionpay_pid;
	// 租户id
    private String tenants_pid;
	// 名称
    private String name;
	// 商户号
    private String mer_number;
	// 消息来源
    private String source;
	// 机构商户号
    private String org;
	// 来源编号
    private String source_number;
	// MD5秘钥
    private String md5;
	// 是否是通用商户号(1：是；0：否)
    private String isCur;

    private String group_pid;
    
    private String group_name;
    
    private String unionpay_name;
	// 租户名称
    private String tenants_name;
    
    private String unionpay_mer_pid;
    
    private String unionpay_mer_name;
    
    private String parent_tenants_pid;
    
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
	 * <b>Summary:设置商户号</b>
	 * setMer_number
	 * @param mer_number
	 */
    public void setMer_number(String mer_number) {
    	this.mer_number = mer_number;
    }
    
    /**
	 * <b>Summary:获取商户号</b>
	 * getMer_number
	 * @return String
	 */
    public String getMer_number() {
    	return mer_number;
    }
	/**
	 * <b>Summary:设置消息来源</b>
	 * setSource
	 * @param source
	 */
    public void setSource(String source) {
    	this.source = source;
    }
    
    /**
	 * <b>Summary:获取消息来源</b>
	 * getSource
	 * @return String
	 */
    public String getSource() {
    	return source;
    }
	/**
	 * <b>Summary:设置机构商户号</b>
	 * setOrg
	 * @param org
	 */
    public void setOrg(String org) {
    	this.org = org;
    }
    
    /**
	 * <b>Summary:获取机构商户号</b>
	 * getOrg
	 * @return String
	 */
    public String getOrg() {
    	return org;
    }
	/**
	 * <b>Summary:设置来源编号</b>
	 * setSource_number
	 * @param source_number
	 */
    public void setSource_number(String source_number) {
    	this.source_number = source_number;
    }
    
    /**
	 * <b>Summary:获取来源编号</b>
	 * getSource_number
	 * @return String
	 */
    public String getSource_number() {
    	return source_number;
    }
	/**
	 * <b>Summary:设置MD5秘钥</b>
	 * setMd5
	 * @param md5
	 */
    public void setMd5(String md5) {
    	this.md5 = md5;
    }
    
    /**
	 * <b>Summary:获取MD5秘钥</b>
	 * getMd5
	 * @return String
	 */
    public String getMd5() {
    	return md5;
    }

	public String getTenants_name() {
		return tenants_name;
	}

	public void setTenants_name(String tenants_name) {
		this.tenants_name = tenants_name;
	}

	public String getGroup_pid() {
		return group_pid;
	}

	public void setGroup_pid(String group_pid) {
		this.group_pid = group_pid;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getUnionpay_name() {
		return unionpay_name;
	}

	public void setUnionpay_name(String unionpay_name) {
		this.unionpay_name = unionpay_name;
	}

	public String getUnionpay_mer_pid() {
		return unionpay_mer_pid;
	}

	public void setUnionpay_mer_pid(String unionpay_mer_pid) {
		this.unionpay_mer_pid = unionpay_mer_pid;
	}

	public String getUnionpay_mer_name() {
		return unionpay_mer_name;
	}

	public void setUnionpay_mer_name(String unionpay_mer_name) {
		this.unionpay_mer_name = unionpay_mer_name;
	}

	public String getParent_tenants_pid() {
		return parent_tenants_pid;
	}

	public void setParent_tenants_pid(String parent_tenants_pid) {
		this.parent_tenants_pid = parent_tenants_pid;
	}

	public String getIsCur() {
		return isCur;
	}

	public void setIsCur(String isCur) {
		this.isCur = isCur;
	}

}