package com.taro.service.sys;

import org.aspectj.lang.ProceedingJoinPoint;

import com.taro.entity.sys.SysLogEntity;
import com.taro.log.annotation.LogAnnotation;
import com.taro.service.IService;

/**
 *<p>Title:SysLogService.java</p>
 *<p>Description:系统日志 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-12-02 21:22:50
 */
public interface SysLogService extends IService<SysLogEntity> {
	
	SysLogEntity saveSysLog(SysLogEntity model);
	
	void logRecord(String module, LogAnnotation logAnnotation, Object object, Exception exception);
	
}