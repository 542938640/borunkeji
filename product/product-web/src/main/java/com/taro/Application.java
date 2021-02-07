package com.taro;import org.mybatis.spring.annotation.MapperScan;import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.EnableAutoConfiguration;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.boot.web.servlet.ServletComponentScan;import org.springframework.cache.annotation.EnableCaching;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.ComponentScan;import org.springframework.context.annotation.Primary;import org.springframework.scheduling.TaskScheduler;import org.springframework.scheduling.annotation.EnableAsync;import org.springframework.scheduling.annotation.EnableScheduling;import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;/*** @ClassName:     HunanstaWebApplication* @Description:    项目启动类* @Author:           Charles* @CreateDate:     2018/12/6 14:28* @Version:        1.0*/@SpringBootApplication(scanBasePackages="com.taro")@MapperScan("com.taro.dao")@ComponentScan({"com.taro","org.myActiviti"})@ServletComponentScan@EnableCaching@EnableAsync // 开启异步任务@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class})@EnableSchedulingpublic class Application {	public static void main(String[] args) {		SpringApplication.run(Application.class, args);		System.out.println("=============================================\n" +				"||        (◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ        ||\n" +				"||        (◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ        ||\n" +				" =============================================");	}	@Primary	@Bean	public TaskScheduler primaryTaskScheduler() {		ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();		return executor;	}}