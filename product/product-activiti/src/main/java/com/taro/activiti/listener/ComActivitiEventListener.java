package com.taro.activiti.listener;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.springframework.stereotype.Component;

import com.taro.activiti.handler.ActivitiEventHandler;
import com.taro.config.ApplicationContextRegister;


/** 
 * Activiti的事件监听器
 * @author  张宇唯
 * @date  2019-01-14
 */  
@Component
public class ComActivitiEventListener implements ActivitiEventListener{

	//事件及事件的处理器  
	@Override  
	public void onEvent(ActivitiEvent event) { 
		
		Map<String,String> handlers = new HashMap<String, String>(); 
		handlers.put("TASK_CREATED", "taskCreateListener");
		
		String eventType = event.getType().name();  
		
		//根据事件的类型ID,找到对应的事件处理器  
		if(handlers.containsKey(eventType)){  
			ActivitiEventHandler handler = (ActivitiEventHandler)ApplicationContextRegister.getApplicationContext().getBean(handlers.get(eventType));
			handler.handle(event);  
		}  
		  
    }

	@Override
	public boolean isFailOnException() {
		return false;
	}
}
