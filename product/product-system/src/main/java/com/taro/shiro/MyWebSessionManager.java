package com.taro.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ClassName: MySessionManager <br/>
 * Function: 自定义session管理器，为了解决大量重复读redis的问题 <br/>
 * date: 2017年10月30日 上午11:18:52 <br/>
 * @author gavin
 * @version
 */
public class MyWebSessionManager extends DefaultWebSessionManager {

    private static final Logger log = LoggerFactory.getLogger(MyWebSessionManager.class);
    /**
     * session 最后操作日期更新的时间间隔（在这个时间段内，如果session只对lastAccessTime做了修改，不去更新session），
     * 这个时间必须小于globalSessionTimeout,默认五分钟
     */
    private long updateSpacing = 5 * 60 * 1000;
    /**
     * session会话过期时间（安全测评需要放代码写死   add by gavin 20180408）
     */
    private long globalSessionTimeout = 24*60*60*1000;
    
    @Override
	public void setGlobalSessionTimeout(long globalSessionTimeout) {
		super.setGlobalSessionTimeout(globalSessionTimeout);
	}
	@Override
	public long getGlobalSessionTimeout() {
		return super.getGlobalSessionTimeout();
	}
	
    public long getUpdateSpacing() {
		return updateSpacing;
	}
	public void setUpdateSpacing(long updateSpacing) {
		this.updateSpacing = updateSpacing;
	}
	
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionId == null) {
            log.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a session could not be found.", sessionKey);
            return null;
        }
        
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (request != null) {
        	ShiroHttpServletRequest req = (ShiroHttpServletRequest)request;
        	
            Object s = request.getAttribute(sessionId.toString());
            if (s != null) {
                return (Session) s;
            } else {
            	String uri = req.getRequestURI();	//静态资源不做处理
            	if(uri != null && (uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".ico")
            						|| uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".gif"))) {
            		return null;
            	}
            }
        }
        
        Session s = retrieveSessionFromDataSource(sessionId);
        if (s == null) {
            //session ID was provided, meaning one is expected to be found, but we couldn't find one:
            String msg = "Could not find session with ID [" + sessionId + "]";
            throw new UnknownSessionException(msg);
        }
        
        if (request != null) {
            request.setAttribute(sessionId.toString(),s);
        }
        
        return s;
    }

    /**
     * 重写touch方法，加入session update策略
     * @see org.apache.shiro.session.mgt.AbstractNativeSessionManager#touch(SessionKey)
     */
	@Override
	public void touch(SessionKey key) throws InvalidSessionException {
        Session s = retrieveSession(key);
        long lastAccessTime = s.getLastAccessTime().getTime();
        s.touch();
        long lastAccessTime_new = s.getLastAccessTime().getTime();
        if((lastAccessTime_new - lastAccessTime) > updateSpacing) {	//在一定时间段内，不去调用sessionDAO.update(),节约性能开支
        	onChange(s);
        }
	}
}