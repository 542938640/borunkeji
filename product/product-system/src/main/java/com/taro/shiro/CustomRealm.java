package com.taro.shiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.taro.entity.sec.SecUserEntity;
import com.taro.service.sec.SecUserService;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private SecUserService secUserService;

	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
//        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Session session = SecurityUtils.getSubject().getSession();
        SecUserEntity user = (SecUserEntity) session.getAttribute("USER_SESSION");
//        // 用户的角色集合
//        Set<String> roles = new HashSet<>();
//        roles.add(user.getRoleList().get(0).getRole());
//        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(secUserService.listPerm(user.getId()));
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SecUserEntity user = secUserService.getByUserName(username);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		user.getUsername(), //用户名
        		user.getPassword(), //密码
                getName()  //realm name
        );
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", user);
        return authenticationInfo;
    }
}
