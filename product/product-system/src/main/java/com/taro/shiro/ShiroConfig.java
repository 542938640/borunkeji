package com.taro.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.taro.config.redis.RedisClientTemplate;

/**
 * shiro配置
 * @author  Charles
 * @date 2018-01-03
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/docs.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");

        filterChainDefinitionMap.put("/editor-app/**", "anon");
        
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/404", "anon");
        

        filterChainDefinitionMap.put("/advertActivities/**", "anon");
        filterChainDefinitionMap.put("/advertEnd/**", "anon");
        filterChainDefinitionMap.put("/advertEndDetail/**", "anon");
        filterChainDefinitionMap.put("/advertHome/**", "anon");
        filterChainDefinitionMap.put("/advertWait/**", "anon");

        filterChainDefinitionMap.put("/merchants/**", "anon");
        filterChainDefinitionMap.put("/merchantsCoupon/**", "anon");

        filterChainDefinitionMap.put("/secTenants/**", "anon");
        
        filterChainDefinitionMap.put("/payRefund/**", "anon");
        
        filterChainDefinitionMap.put("/helpAdvise/**", "anon");
        filterChainDefinitionMap.put("/helpCenter/**", "anon");
        
        filterChainDefinitionMap.put("/app/**", "anon");

        filterChainDefinitionMap.put("/fileManage/**", "anon");

        filterChainDefinitionMap.put("/pubExportConfig/**", "anon");
        
        filterChainDefinitionMap.put("/**", "authc");
//        filterChainDefinitionMap.put("/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/sysLogin", "anon");
//        filterChainDefinitionMap.put("/sysLoginByCode", "anon");
//        filterChainDefinitionMap.put("/sysLoginWithoutVerifyCode", "anon");
//        filterChainDefinitionMap.put("/verifyCode", "anon");
//        filterChainDefinitionMap.put("/userinfoext/changePassword", "anon");
//        filterChainDefinitionMap.put("/sysLogin", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
        // 登录成功后要跳转的链接
        // shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        
        LinkedHashMap<String, Filter> filtsMap=new LinkedHashMap<>();
        filtsMap.put("authc", new ShiroFormAuthenticationFilter() );
        shiroFilterFactoryBean.setFilters(filtsMap);
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public SecurityRealm myShiroRealm() {
        SecurityRealm myShiroRealm = new SecurityRealm();
        //myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        MyWebSessionManager sessionManager = new MyWebSessionManager();
        //设置session过期时间30分钟 单位毫秒
        sessionManager.setGlobalSessionTimeout(10800000L);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationInterval(10800000L);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(shareSession());
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    @Bean
    public SimpleCookie shareSession(){
        SimpleCookie shareSession = new SimpleCookie("SHAREJSESSIONID");
        shareSession.setPath("/");
        shareSession.setHttpOnly(true);
        return shareSession;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDao redisSessionDAO() {
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        redisSessionDao.setTemplate(redisClientTemplate);
        redisSessionDao.setTimeToLiveSeconds(10800);
        return redisSessionDao;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }
}
