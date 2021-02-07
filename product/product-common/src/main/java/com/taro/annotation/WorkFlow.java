package com.taro.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkFlow {
	/**
	*<b>Summary: 业务模块的业务id对应在在实体中的属性</b>
	*<b>没有默认值，如果引用了此注解就必须设置性</b>
	* @return
	*/
	public String id();
	
	/**
	 * isStartAndComplete:(流程是否启动并送下个环节，默认是只启动流程，如果为true，则启动并送到第二个环节去). <br/>
	 *
	 * @author gavin
	 * @return
	 */
	public boolean isStartAndComplete() default false;
	
	/**
	 * titleField:(流程标题字段在业务对象中的属性值，如果设置了，将利用反射自动取此字段的值作为流程标题). <br/>
	 * @author gavin
	 * @return
	 */
	public String titleField() default "";
}