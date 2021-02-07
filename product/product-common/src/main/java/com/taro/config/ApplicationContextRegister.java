package com.taro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * springboot无论以main方法还是spring-boot:run的方式执行都不会跑SpringBootServletInitializer中的onStartup导致ContextLoaderListener没有执行。
 * 考虑到以往的经验ContextLoaderListener一般是配置在web.xml中的对web容器有依赖，
 * 所以我直接把工程打成war放到tomcat跑果然可以调用SpringBootServletInitializer中的onStartup，
 * 但是还是不能获取ContextLoader.getCurrentWebApplicationContext()，
 * 原因是在onStartup初始化ContextLoader时使用的是构造函数，
 * 没有用ContextLoader.initWebApplicationContext方式，所以获取不到
 *
 * 使用ApplicationContextAware 的getApplicationContext方法
 * @author  Charles
 * @date  2018-12-03
 */
@Component
@Lazy(false)
public class ApplicationContextRegister implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextRegister.class);
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 设置spring上下文
     *@param applicationContext
     * spring上下文
     * @throws BeansException
     * */
    @Override  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.debug("ApplicationContext registed-->{}", applicationContext);
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
}
