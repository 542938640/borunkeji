package com.taro.activiti.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.taro.annotation.WorkFlow;
import com.taro.entity.ProcessDataEntity;
import com.taro.entity.WorkFlowBean;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.activiti.ActivityService;
import com.taro.utils.ClassRefUtil;
import com.taro.utils.SecurityMyUtils;

/**
 *<p>Description:流程操作切面 </p>
 *@author 张宇唯
 *@version 1.0
 *2019-3-5
 */
@Aspect
@Component
public class WorkFlowAOP {
	private static final Logger LOG = Logger.getLogger(WorkFlowAOP.class);

	private static final String METHOD_OF_GET_WORKFLOW_DATA = "getWorkFlowData";	//利用反射获取业务流程数据对象的bean
	
	@Autowired
	private ActivityService activityService;	//流程操作相关接口

	//execution为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包 *(..)代表各种方法.  &&  @annotation(workFlow) 表示并且方法上有为workFlow的注解
	//匹配服务层实现类的所有以AutoWorkFlow结尾的方法为切入点
	@Pointcut(value="execution(* com.taro.service..*.*AutoWorkFlow(..)) && @annotation(workFlow) " , argNames="workFlow")
    private void autoWorkFlow(WorkFlow workFlow){}//定义流程切入点 

	@Pointcut(value="execution(* com.taro.service..*.*AutoTodoList(..)) && @annotation(workFlow)" , argNames="workFlow")
    private void autoTodoList(WorkFlow workFlow){}//定义待办列表切入点 
	
	@Pointcut(value="execution(* com.taro.service..*.*AutoDoneList(..)) && @annotation(workFlow)" , argNames="workFlow")
    private void autoDoneList(WorkFlow workFlow){}//定义已办列表切入点 
	
	/**
	 * 执行流程相关操作方法
	 * @param point
	 * @param workFlow
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "autoWorkFlow(workFlow)" , argNames="workFlow")
    public Object autoWorkFlow(ProceedingJoinPoint point,WorkFlow workFlow) throws Throwable {

		long start = System.currentTimeMillis();
        LOG.info("WORKFLOW--->进入流程AOP");
        
        Object object = point.proceed();//执行方法

        //拿到返回值，处理相关数据
        if(null != object){
        	//拿到流程对象
        	Method m = object.getClass().getMethod(METHOD_OF_GET_WORKFLOW_DATA);
        	WorkFlowBean workFlowBean = (WorkFlowBean)m.invoke(object);
        	
        	if(null != workFlowBean){
        		//业务主键
        		if(StringUtils.isBlank(workFlowBean.getBusi_key())){	//利用注解获取业务id的字段，通过反射获取字段值
        			//这里的主键id都明显约定在父类的父类里面，所以这里直接用.getSuperclass().getSuperclass()，避免多层递归了
        			String busi_key = String.valueOf(ClassRefUtil.getField(object.getClass().getSuperclass().getSuperclass(),object, workFlow.id()));
        			if(StringUtils.isBlank(busi_key)){
        				throw new BusinessException("无业务主键!");
        			}
        			workFlowBean.setBusi_key(busi_key);
        		}
        		//代办标题
        		if(StringUtils.isBlank(workFlowBean.getTitle()) && StringUtils.isNotBlank(workFlow.titleField())){
        			String title = String.valueOf(ClassRefUtil.getField(object.getClass(),object, workFlow.titleField()));
        			workFlowBean.setTitle(title);
        		}
        		
        		ProcessDataEntity processData = null;

        		String variables_json = workFlowBean.getVariables_json();
        		Map<String, Object> variablesMap = new HashMap<String, Object>();	
        		if(StringUtils.isNotBlank(variables_json)) {
        			variablesMap = JSON.parseObject(variables_json, Map.class);
        		}
        		
        		if(StringUtils.isNotBlank(workFlowBean.getComment())){//附加信息
        			variablesMap.put("comment", workFlowBean.getComment());
        		}

    			String assignee = workFlowBean.getAssignee();
    			if(StringUtils.isBlank(assignee)){
//    				assignee = SecurityMyUtils.getUserId();//若为空,则取当前登录人
    			}
    			
        		//是否需要启动流程
        		if(null != workFlowBean.getIs_start() && 1 == workFlowBean.getIs_start()){
        			String start_user = workFlowBean.getStart_user();
        			if(StringUtils.isBlank(start_user)){
        				start_user = SecurityMyUtils.getUserId();//默认启动环节取当前登录人
        			}
        			//启动并走下一步
        			if(workFlow.isStartAndComplete()){
            			processData = activityService.startAndComplete(workFlowBean.getBusi_key(), workFlowBean.getFlow_key(), assignee, variablesMap);
        			}else{
            			processData = activityService.start(workFlowBean.getBusi_key(), workFlowBean.getFlow_key(), variablesMap);
        			}
        		}else{//完成流程环节
        			processData = activityService.completeTaskByBusiKey(workFlowBean.getBusi_key(), workFlowBean.getFlow_key(), assignee, variablesMap);
        		}
        		
        		//执行回调
        		if(null != processData && point.getTarget() instanceof AbstractService){
                    AbstractService target = (AbstractService)point.getTarget();
                    target.workFlowCallback(processData);
        		}
        	}
        }

        LOG.info("WORKFLOW--->退出流程AOP方法。方法执行时长: "+ (System.currentTimeMillis() - start) + "ms");
        return object;
	}
	

	@Around(value = "autoTodoList(workFlow)" , argNames="workFlow")
    public Object autoTodoList(ProceedingJoinPoint point,WorkFlow workFlow) throws Throwable {
		long start = System.currentTimeMillis();
		LOG.info("WORKFLOW--->进入流程待办列表AOP");
		
		//设置参数
        Object[] args = point.getArgs();
        
        //这里必须指定，查询参数必须是第三个参数！！！！
        Map<String, Object> map = new HashMap<String, Object>();
        if (args != null && args.length > 0 && args[2] instanceof Map) {
        	map = (Map<String, Object>)args[2];
        	
        	String[] idArr = activityService.queryBusiIdByTodoList(String.valueOf(map.get("flow_key")), 
        		String.valueOf(map.get("user_id")), String.valueOf(map.get("task_def")));
        	//没有查出来数据则插入-1避免查出数据
        	if(null == idArr){
        		idArr = new String[1];
        		idArr[0] = "-1";
        	}
    		map.put("busiIds", idArr);
        }
        
        Object object = point.proceed(args);//执行方法
        
        LOG.info("WORKFLOW--->退出流程待办列表AOP方法。方法执行时长: "+ (System.currentTimeMillis() - start) + "ms");
        return object;
	}

	@Around(value = "autoDoneList(workFlow)" , argNames="workFlow")
    public Object autoDoneList(ProceedingJoinPoint point,WorkFlow workFlow) throws Throwable {
		long start = System.currentTimeMillis();
		LOG.info("WORKFLOW--->进入流程已办列表AOP");
		
		//设置参数
        Object[] args = point.getArgs();
        
        //这里必须指定，查询参数必须是第三个参数！！！！
        Map<String, Object> map = new HashMap<String, Object>();
        if (args != null && args.length > 0 && args[2] instanceof Map) {
        	map = (Map<String, Object>)args[2];
        	
        	String[] idArr = activityService.queryBusiIdByDoneList(String.valueOf(map.get("flow_key")), 
        		String.valueOf(map.get("user_id")), String.valueOf(map.get("task_def")));
        	//没有查出来数据则插入-1避免查出数据
        	if(null == idArr){
        		idArr = new String[1];
        		idArr[0] = "-1";
        	}
    		map.put("busiIds", idArr);
        }
        
        Object object = point.proceed(args);//执行方法
        
        LOG.info("WORKFLOW--->退出流程已办列表AOP方法。方法执行时长: "+ (System.currentTimeMillis() - start) + "ms");
        return object;
	}
}
