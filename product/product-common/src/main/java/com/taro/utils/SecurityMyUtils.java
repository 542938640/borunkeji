package com.taro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;

import com.taro.constants.Constant;

/**  
 * @File: SecurityUtils.java   
 * @Description: TODO 
 * @Author: 张宇唯  
 * @Date: 2017年1月6日  
 * @Copyright taro(c)2010-2017
 */
public class SecurityMyUtils {

	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		Subject currentUser = SecurityUtils.getSubject();  
		return currentUser.getSession();
	}
	
	/**
	 * 在session中查询数据 没有则返回admin
	 * @return
	 */
	public static String getUserIdAndAdmin(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			String userId=SecurityMyUtils.getUserId();
			if (userId!=null){
				return userId;
			}else{
				return Constant.ADMINID;
			}
		}else {
			return Constant.ADMINID;
		}
	}
	
	/**获取当前登录的用户名
	 * @return
	 */
	public static String getUserName(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return getSession().getAttribute(Constant.SESSION_USERNAME).toString();
		}
		return "";
	}
	
	/**获取当前登录的编码
	 * @return
	 */
	public static String getUserCode(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return getSession().getAttribute(Constant.SESSION_USERCODE).toString();
		}
		return "";

	}
	
	/**获取当前登录的主键
	 * @return
	 */
	public static String getUserId(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return getSession().getAttribute(Constant.SESSION_USERID).toString();
		}
		return "";
	}
	
	/**
	 * 查询当前登录人路由
	 * @return
	 */
	public static String getOrgRoute(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return getSession().getAttribute(Constant.SESSION_ORGROUTE).toString();
		}
		return "";
	}
	
	/**
	 * 获取当前登录用户组织结构id
	 * @return
	 */
	public static String getOrgId(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return getSession().getAttribute(Constant.SESSION_ORGID).toString();
		}
		return "";
	}

	
	/**
	 * 获取当前登录用户的租户id
	 * @return
	 */
	public static String getTenantsId(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			return getSession().getAttribute(Constant.SESSION_TENANTSID).toString();
		}
		return "";
	}
}
