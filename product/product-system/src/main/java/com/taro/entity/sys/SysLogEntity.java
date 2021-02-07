package com.taro.entity.sys;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SysLogEntity.java</p>
 *<p>Description:系统日志 Entity(对应表名:t_sys_log)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-12-02 23:49:34
 */
public class SysLogEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 日志类型
    private String type;
	// 模块
    private String module;
	// 功能
    private String function;
	// 日志内容
    private String operate;
	// 操作状态(1=成功,0=失败)
    private String status;
	// 失败原因
    private String failure_reason;
	// 操作者IP
    private String ip_addr;

	/**
	 * <b>Summary:设置日志类型</b>
	 * setType
	 * @param type
	 */
    public void setType(String type) {
    	this.type = type;
    }
    
    /**
	 * <b>Summary:获取日志类型</b>
	 * getType
	 * @return String
	 */
    public String getType() {
    	return type;
    }
	/**
	 * <b>Summary:设置模块</b>
	 * setModule
	 * @param module
	 */
    public void setModule(String module) {
    	this.module = module;
    }
    
    /**
	 * <b>Summary:获取模块</b>
	 * getModule
	 * @return String
	 */
    public String getModule() {
    	return module;
    }
	/**
	 * <b>Summary:设置功能</b>
	 * setFunction
	 * @param function
	 */
    public void setFunction(String function) {
    	this.function = function;
    }
    
    /**
	 * <b>Summary:获取功能</b>
	 * getFunction
	 * @return String
	 */
    public String getFunction() {
    	return function;
    }
	/**
	 * <b>Summary:设置日志内容</b>
	 * setOperate
	 * @param operate
	 */
    public void setOperate(String operate) {
    	this.operate = operate;
    }
    
    /**
	 * <b>Summary:获取日志内容</b>
	 * getOperate
	 * @return String
	 */
    public String getOperate() {
    	return operate;
    }
	/**
	 * <b>Summary:设置操作状态(1=成功,0=失败)</b>
	 * setStatus
	 * @param status
	 */
    public void setStatus(String status) {
    	this.status = status;
    }
    
    /**
	 * <b>Summary:获取操作状态(1=成功,0=失败)</b>
	 * getStatus
	 * @return String
	 */
    public String getStatus() {
    	return status;
    }
	/**
	 * <b>Summary:设置失败原因</b>
	 * setFailure_reason
	 * @param failure_reason
	 */
    public void setFailure_reason(String failure_reason) {
    	this.failure_reason = failure_reason;
    }
    
    /**
	 * <b>Summary:获取失败原因</b>
	 * getFailure_reason
	 * @return String
	 */
    public String getFailure_reason() {
    	return failure_reason;
    }
	/**
	 * <b>Summary:设置操作者IP</b>
	 * setIp_addr
	 * @param ip_addr
	 */
    public void setIp_addr(String ip_addr) {
    	this.ip_addr = ip_addr;
    }
    
    /**
	 * <b>Summary:获取操作者IP</b>
	 * getIp_addr
	 * @return String
	 */
    public String getIp_addr() {
    	return ip_addr;
    }

}