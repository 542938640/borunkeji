package com.taro.entity.market;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;
import java.sql.Date;

/**
 *<p>Title:OwnProductsEntity.java</p>
 *<p>Description: Entity(对应表名:ownproducts)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-26 23:48:00
 */
public class OwnProductsEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 
    private String productid;
	// 
    private String tenantid;
	// 
    private Integer isdeleted;
	// 
    private Date creationtime;
	// 
    private String creatorid;
	// 
    private Date lastmodificationtime;
	// 
    private String lastmodifierid;

	/**
	 * <b>Summary:设置</b>
	 * setProductid
	 * @param productid
	 */
    public void setProductid(String productid) {
    	this.productid = productid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getProductid
	 * @return String
	 */
    public String getProductid() {
    	return productid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setTenantid
	 * @param tenantid
	 */
    public void setTenantid(String tenantid) {
    	this.tenantid = tenantid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getTenantid
	 * @return String
	 */
    public String getTenantid() {
    	return tenantid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setIsdeleted
	 * @param isdeleted
	 */
    public void setIsdeleted(Integer isdeleted) {
    	this.isdeleted = isdeleted;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getIsdeleted
	 * @return Integer
	 */
    public Integer getIsdeleted() {
    	return isdeleted;
    }
	/**
	 * <b>Summary:设置</b>
	 * setCreationtime
	 * @param creationtime
	 */
    public void setCreationtime(Date creationtime) {
    	this.creationtime = creationtime;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getCreationtime
	 * @return Date
	 */
    public Date getCreationtime() {
    	return creationtime;
    }
	/**
	 * <b>Summary:设置</b>
	 * setCreatorid
	 * @param creatorid
	 */
    public void setCreatorid(String creatorid) {
    	this.creatorid = creatorid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getCreatorid
	 * @return String
	 */
    public String getCreatorid() {
    	return creatorid;
    }
	/**
	 * <b>Summary:设置</b>
	 * setLastmodificationtime
	 * @param lastmodificationtime
	 */
    public void setLastmodificationtime(Date lastmodificationtime) {
    	this.lastmodificationtime = lastmodificationtime;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getLastmodificationtime
	 * @return Date
	 */
    public Date getLastmodificationtime() {
    	return lastmodificationtime;
    }
	/**
	 * <b>Summary:设置</b>
	 * setLastmodifierid
	 * @param lastmodifierid
	 */
    public void setLastmodifierid(String lastmodifierid) {
    	this.lastmodifierid = lastmodifierid;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getLastmodifierid
	 * @return String
	 */
    public String getLastmodifierid() {
    	return lastmodifierid;
    }

}