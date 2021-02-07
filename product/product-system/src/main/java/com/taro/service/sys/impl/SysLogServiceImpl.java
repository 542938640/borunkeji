package com.taro.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import com.taro.exception.BusinessException;
import com.taro.log.annotation.LogAnnotation;
import com.alibaba.fastjson.JSONObject;
import com.taro.dao.sys.SysLogDao;
import com.taro.entity.sys.SysLogEntity;
import com.taro.service.AbstractService;
import com.taro.service.sys.SysLogService;

/**
 *<p>Title:SysLogServiceImpl.java</p>
 *<p>Description:系统日志 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-12-02 21:24:31
 */
@Service
public class SysLogServiceImpl extends AbstractService<SysLogEntity> implements SysLogService {
	
	@Autowired
	private SysLogDao sysLogDao;
	
    @Override
    protected SysLogDao getDao() {
        return sysLogDao;
    }
    
    @Override
    public SysLogEntity saveSysLog (SysLogEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }
    
    @Async
    @Override
    public void logRecord(String module, LogAnnotation logAnnotation, Object object, Exception exception) {
//        // 功能
//        String function = logAnnotation.function();
        // 操作类型
        String type = logAnnotation.type();
        // 操作内容
        String operate = logAnnotation.operate();
        if(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(operate)) {
            String status = "-999";
            SysLogEntity log = new SysLogEntity();
            if(null != exception) {// 方法异常
            	status = "0";
            	log.setFailure_reason(exception.getMessage());
            }else if(null == object) {// 无返回值,默认成功
            	status = "1";
            }else if(object instanceof String) {
        		String returnStr = String.valueOf(object);
        		try {
        			JSONObject returnJson = JSONObject.parseObject(returnStr);
        			if(returnJson.containsKey("status")) {
            			status = returnJson.getString("status");
        			}
        		}catch (Exception e) {
                	status = "1"; // 返回的String不是JSON字符串,则默认成功
				}
            }
            if(!"-999".equals(status)) {
            	log.setModule(module);
            	log.setType(type);
            	log.setOperate(operate);
            	log.setStatus(status);
        		super.save(log);
            }
        }
    }
}