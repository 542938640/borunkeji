package com.taro.controller.activiti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taro.controller.AbstractController;
import com.taro.entity.activiti.ProcessVariableEntity;
import com.taro.service.activiti.ProcessVariableService;

/**
 *<p>Title:ProcessVariableController.java</p>
 *<p>Description:流程变量 Action</p>
 *@author Ann
 *@version 1.0
 *@Automatically generate by Coder in 2019-03-19 14:45
 */
@RestController
@RequestMapping(value="/processvariable")
public class ProcessVariableController extends AbstractController<ProcessVariableEntity>{

	public final static String MODULE = "流程变量";

	public final static String ENTITY = "ProcessVariableEntity";
	
	@Autowired
	private ProcessVariableService processVariableService;
	
	@Override
    protected ProcessVariableService getService () {
        return processVariableService;
    }

	@Override
    public String getModule() {
		return MODULE;
	}

}