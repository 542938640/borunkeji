package com.taro.controller.sec;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.constants.Constant;
import com.taro.entity.sec.SecUserEntity;
import com.taro.exception.BusinessException;
import com.taro.service.sec.SecUserService;
import com.taro.utils.MD5Utils;
import com.taro.utils.RSAEncrypt;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:LoginController.java</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@RestController
public class LoginController{
	@Autowired
	private SecUserService secUserService;
	

	@ApiOperation(value = "登录接口", notes = "登录接口")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "登录参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/login", produces = Constant.JSON_UTF_8, consumes = Constant.JSON_UTF_8, method = RequestMethod.POST)
	public String Login(@RequestBody Map<String, String> parameter, HttpServletRequest req, HttpServletResponse resp) {
		String sign = parameter.get("_sign_");
		if(StringUtils.isBlank(sign) || "null".equals(sign)) {
			throw new BusinessException("用户名或密码错误!");
		}
		
		//判断密码是否正确
		try {
			//先RSA解密成明文
			sign = RSAEncrypt.decrypt(sign);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("用户名或密码错误!");
		}


		JSONObject signObj = JSON.parseObject(sign);

		String username = signObj.getString("username");
		if(StringUtils.isBlank(username) || "null".equals(username)) {
			throw new BusinessException("用户名不能为空!");
		}

		String password = signObj.getString("password");
		if(StringUtils.isBlank(password) || "null".equals(password)) {
			throw new BusinessException("密码不能为空!");
		}
		
		SecUserEntity user = secUserService.getByUserName(username);
		if(null == user) {
			throw new BusinessException("用户名或密码错误!");
		}

		//MD5加盐加密
		password = MD5Utils.getSaltMD5(password, user.getSalt());
		if(!password.equals(user.getPassword())) {
			throw new BusinessException("用户名或密码错误!");
		}
		
		//登录成功
		Subject subject = SecurityUtils.getSubject();// 获取Subject单例对象
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());//使用用户的登录信息创建令牌
		try {
			subject.logout();
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
    		return new Message(Constant.STATUS_ERROR_GENERAL,"用户名或密码错误!").toJson();
        } catch (AuthorizationException e) {
            e.printStackTrace();
    		return new Message(Constant.STATUS_ERROR_GENERAL,"用户名或密码错误!").toJson();
        }
        
		user.setPassword("");
		user.setSalt("");
		user.setStatus("");
		user.setLogin_status("");
		user.setPermList(secUserService.listPerm(user.getId()));
		return new DataSetObject<SecUserEntity>(user).toJson();
	}


}