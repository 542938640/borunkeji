package com.taro.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
@Documented
public @interface LogAnnotation {
	/**模块*/
    String module() default "";
	/**功能*/
    String function() default "";
    /**操作类型*/
    String type() default "";
    /**操作内容*/
    String operate() default "";
}
