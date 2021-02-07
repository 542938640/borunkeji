package com.taro.common.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.taro.config.redis.RedisClientTemplate;

/**
 * ClassName:SpringApplicationListener <br/>
 * Date:     2017-10-24 上午11:14:32 <br/>
 * @author   gavin
 * @since    JDK 1.6
 */
public class SpringApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger LOG = Logger.getLogger(SpringApplicationListener.class);
	private static XmlWebApplicationContext springContext = null;
	public static Integer OK = 0;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		XmlWebApplicationContext context = (XmlWebApplicationContext)event.getApplicationContext();
		if(context != null && context.getParent() == null){
			try {
				Init.verify();
				OK = 1;
				springContext = context;
				
				//记录系统启动时间，此时间用于计算系统运行时长。
				RedisClientTemplate redisClientTemplate = springContext.getBean(RedisClientTemplate.class);
				redisClientTemplate.set("system_startup_time", String.valueOf(System.currentTimeMillis()));
			} catch (Exception e) {
				OK = 0;
				context.destroy();
				LOG.error(e.getMessage(), e);
			}
		}
	}
	
	@Scheduled(cron = "0 0 2 * * ? ")
	public void verify(){
		if(springContext != null){
			try {
				Init.verify();
				OK = 1;
			} catch (Exception e) {
				OK = 0;
				springContext.destroy();
				LOG.error(e.getMessage(), e);
			}
		}
	}
}