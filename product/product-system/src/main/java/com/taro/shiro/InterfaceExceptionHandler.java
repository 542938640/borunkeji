package com.taro.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taro.common.Message;
import com.taro.constants.Constant;
import com.taro.exception.BusinessException;
 
 
 
/**
 * 自定义异常处理器
 *
 * @author ieflex
 */
//@RestControllerAdvice
public class InterfaceExceptionHandler {
//
//    private static final Logger log = LoggerFactory.getLogger(InterfaceExceptionHandler.class);
//
//	/**
//	 * -没有权限
//	 */
//	@ResponseBody
//	@ExceptionHandler(AuthenticationException.class)
//	public String authorizationException(AuthenticationException e) {
//		e.printStackTrace();
//		return new Message(Constant.STATUS_ERROR_LOGIN, "对不起，您未登录或已超时！请重新登录。").toJson();
//	}
//	
//	/**
//	 * -没有权限
//	 */
//	@ResponseBody
//	@ExceptionHandler(AuthorizationException.class)
//	public String authorizationException(AuthorizationException e) {
//		e.printStackTrace();
//		return new Message(Constant.STATUS_ERROR_AUTH, "对不起，您没有该权限！请联系管理员。").toJson();
//	}
//	
//	/**AuthorizationException
//	 * 接口 业务异常
//	 */
//	@ResponseBody
//	@ExceptionHandler(BusinessException.class)
//	public String businessException(BusinessException e) {
//		e.printStackTrace();
//		return new Message(Constant.STATUS_ERROR_SYSTEM, e.getMessage()).toJson();
//	}
// 
//	/**
//	 * 拦截所有运行时的全局异常   
//	 */
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseBody
//	public String runtimeException(RuntimeException e) {
//		e.printStackTrace();
//		return new Message(Constant.STATUS_ERROR_SYSTEM, "系统错误！").toJson();
//	}
// 
//	/**
//	 * 系统异常捕获处理
//	 */
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public String exception(Exception e) {
//		e.printStackTrace();
//		return new Message(Constant.STATUS_ERROR_SYSTEM, "系统错误！").toJson();
//	}
}
