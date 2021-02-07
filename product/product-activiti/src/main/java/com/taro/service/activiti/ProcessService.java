package com.taro.service.activiti;

import com.taro.entity.activiti.ProcessEntity;
import com.taro.service.IService;

/**
 *<p>Title:ProcessService.java</p>
 *<p>Description:流程表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 16:55
 */
public interface ProcessService extends IService<ProcessEntity> {

	ProcessEntity saveProcess(ProcessEntity model);

	ProcessEntity getProcess(String id);
	
    int deleteProcess(String ids);
}