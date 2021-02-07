package com.taro.shiro;

 import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.taro.constants.Constant;
import com.taro.exception.BusinessException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
 import org.apache.shiro.authz.UnauthorizedException;
 import org.springframework.web.servlet.HandlerExceptionResolver;
 import org.springframework.web.servlet.ModelAndView;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.util.HashMap;
 import java.util.Map;

/**
 * shiro全局异常处理
 * @author  Charles
 * @date 2018-01-03
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<>();
        ex.printStackTrace();
        if (ex instanceof UnauthenticatedException) {
            attributes.put("status", Constant.STATUS_ERROR_LOGIN);
            attributes.put("msg", "token错误");
        } else if (ex instanceof UnauthorizedException || ex instanceof AuthorizationException) {
            attributes.put("status", Constant.STATUS_ERROR_AUTH);
            attributes.put("msg", "对不起，您没有该权限！请联系管理员。");
        } else if (ex instanceof AuthenticationException) {
            attributes.put("status", Constant.STATUS_ERROR_LOGIN);
            attributes.put("msg", "对不起，您未登录或已超时！请重新登录。");
        } else if (ex instanceof BusinessException) {
            attributes.put("status", Constant.STATUS_ERROR_SYSTEM);
            attributes.put("msg", ex.getMessage());
        } else {
            attributes.put("status", Constant.STATUS_ERROR_SYSTEM);
            attributes.put("msg", "系统错误！");
        }

        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}

