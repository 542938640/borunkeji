package com.taro.tenants.aop;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.taro.entity.AbstractEntity;
import com.taro.utils.SecurityMyUtils;

/**
 *<p>Description: 租户切面 </p>
 *@author 张宇唯
 *@version 1.0
 *2019-3-5
 */
@Aspect
@Component
public class TenantsAop {
	//execution为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包 *(..)代表各种方法.  &&  @annotation(logAnnotation) 表示并且方法上有为logAnnotation的注解
	@Pointcut(value="execution(* com.taro.controller..*.*(..))")
    private void tenantsRecord(){}//定义流程切入点 
	
	/**
	 * 执行流程相关操作方法
	 * @param point
	 * @param workFlow
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "tenantsRecord()")
    public Object tenantsRecord(ProceedingJoinPoint point) throws Throwable {
        // 获取所有的参数
        Object[] args = point.getArgs();
        
        try {
            // 方法参数不为空且方法参数为1
            if(null != args) {
            	if(args.length == 1) {
    	        	Object arg = args[0];
    	        	if(arg instanceof Map) {// 参数为map,认为是查询接口
    	        		Map map = ((Map) arg);
    	        		if(!map.containsKey("tenants_pid")) {
    	        			map.put("tenants_pid", SecurityMyUtils.getTenantsId());
    	        		}
    	        		arg = map;
    	        	}else if(arg instanceof AbstractEntity) {// 参数为AbstractEntity,认为是保存接口
    	        		((AbstractEntity) arg).setTenants_pid(SecurityMyUtils.getTenantsId());
    	        	}
    	        	args[0] = arg;
            	}else if(args.length == 3
            			&& args[0] instanceof Integer
            			&& args[1] instanceof Integer
            			&& args[2] instanceof Map){
    	        	Object arg = args[2];
            		Map map = ((Map) arg);
            		if(!map.containsKey("tenants_pid")) {
            			map.put("tenants_pid", SecurityMyUtils.getTenantsId());
            		}
    	        	args[2] = map;
            	}
            }
        }catch (Exception e) {
        	args = point.getArgs();
		}
        
        Exception exception = null;
        Object object = null;
        
        try {
        	object = point.proceed(args);//执行方法
        }catch (Exception e) {
        	exception = e;
		}

        // 抛出本来的异常
        if(null != exception) {
        	throw exception;
        }
        return object;
	}
}
