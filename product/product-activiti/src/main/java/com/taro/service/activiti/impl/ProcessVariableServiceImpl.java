package com.taro.service.activiti.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.activiti.ProcessVariableDao;
import com.taro.entity.activiti.ProcessVariableEntity;
import com.taro.service.AbstractService;
import com.taro.service.activiti.ProcessVariableService;

/**
 *<p>Title:ProcessVariableServiceImpl.java</p>
 *<p>Description:流程变量ServiceImpl</p>
 *@author Ann
 *@version 1.0
 *@Automatically generate by Coder in 2019-03-19 14:45
 */
@Service
public class ProcessVariableServiceImpl extends AbstractService<ProcessVariableEntity> implements ProcessVariableService{
	@Autowired
	private ProcessVariableDao processVariableDao;
    @Override
    protected ProcessVariableDao getDao () {
        return processVariableDao;
    }
    
}