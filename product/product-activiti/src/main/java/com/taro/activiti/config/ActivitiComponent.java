package com.taro.activiti.config;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taro.activiti.listener.ComActivitiEventListener;

/** 
 * Activiti的工作流配置
 * @author  张宇唯
 * @date  2019-01-14
 */  
@Component
public class ActivitiComponent implements ProcessEngineConfigurationConfigurer {
    
    @Autowired
    private ComActivitiEventListener comActivitiEventListener;

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {

    	List<ActivitiEventListener> activitiEventListener=new ArrayList<ActivitiEventListener>();

    	activitiEventListener.add(comActivitiEventListener);//配置全局监听器

    	processEngineConfiguration.setEventListeners(activitiEventListener);
    	
    	processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setAnnotationFontName("宋体");
    }

}
