package com.taro.activiti.handler;

import org.activiti.engine.delegate.event.ActivitiEvent;

/** 
 * Activiti的事件处理器 
 * @author  张宇唯
 * @date  2019-01-14
 */  
public interface ActivitiEventHandler {
	/** 
	  * 事件处理器 
	  * @param event 
	  */  
	 public void handle(ActivitiEvent event); 
}
