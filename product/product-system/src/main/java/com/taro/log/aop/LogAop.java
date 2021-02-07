package com.taro.log.aop;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taro.controller.AbstractController;
import com.taro.log.annotation.LogAnnotation;
import com.taro.service.sys.SysLogService;



/**
 *<p>Description:日志切面 </p>
 *@author 张宇唯
 *@version 1.0
 *2019-3-5
 */
@Aspect
@Component
public class LogAop {
	private static final Logger LOG = Logger.getLogger(LogAop.class);

	@Autowired
	private SysLogService sysLogService;	//系统日志
	
	//execution为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包 *(..)代表各种方法.  &&  @annotation(logAnnotation) 表示并且方法上有为logAnnotation的注解
	@Pointcut(value="execution(* com.taro.controller..*.*(..)) && @annotation(logAnnotation) " , argNames="logAnnotation")
    private void logRecord(LogAnnotation logAnnotation){}//定义流程切入点 
	

	/**
	 * 执行流程相关操作方法
	 * @param point
	 * @param workFlow
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "logRecord(logAnnotation)" , argNames="logAnnotation")
    public Object logRecord(ProceedingJoinPoint point, LogAnnotation logAnnotation) throws Throwable {

		long start = System.currentTimeMillis();
        LOG.info("LOGRECORD--->进入日志AOP");
        
        // 模块
        String module = "";
        if(point.getTarget() instanceof AbstractController) {
        	AbstractController target = (AbstractController)point.getTarget();
        	module = target.getModule();
        }
        
        Exception exception = null;
        Object object = null;
        
        try {
        	object = point.proceed();//执行方法
        }catch (Exception e) {
        	exception = e;
		}
        
        // 异步记录日志
        if(StringUtils.isNotBlank(module)) {
        	sysLogService.logRecord(module, logAnnotation, object, exception);
        }

        // 抛出本来的异常
        if(null != exception) {
        	throw exception;
        }
        
        LOG.info("LOGRECORD--->退出日志AOP方法。方法执行时长: "+ (System.currentTimeMillis() - start) + "ms");
        return object;
	}
}
