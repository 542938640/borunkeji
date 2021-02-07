package com.taro.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.taro.constants.Constant;
import com.taro.entity.sec.SecUserEntity;
import com.taro.service.sec.SecUserService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.SecurityMyUtils;

/**  
 * @Package: com.taro.tdevice.security  
 * @File: SecurityRealm.java   
 * @Description: 自定义的指定Shiro验证用户登录的类 
 * @Author: tanquanlong  
 * @Date: 2016年12月15日  
 * @Copyright taro(c)2010-2016
 */
public class SecurityRealm extends AuthorizingRealm{
	
	@Autowired
	private SecUserService secUserService;
	
	/**
	 * 为当前登录的Subject授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 验证当前登录的Subject
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SecUserEntity user = secUserService.getByUserName(token.getUsername());
		if (user != null) {
			 AuthenticationInfo authcInfo =  new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getUsername());
			 Session session = SecurityMyUtils.getSession();
			 if(session!=null){
				 session.setAttribute(Constant.SESSION_USERID, user.getId());
				 session.setAttribute(Constant.SESSION_USERNAME, user.getUsername());
				 session.setAttribute(Constant.SESSION_USERCODE, user.getUsername());
				 session.setAttribute(Constant.SESSION_ORGID, user.getId());
				 session.setAttribute(Constant.SESSION_TENANTSID, MyStringUtil.isNotBlank(user.getTenants_pid()) ? user.getTenants_pid() : "");
			 }
			 return authcInfo;
		} else {
			return null;
		}
	}
}
