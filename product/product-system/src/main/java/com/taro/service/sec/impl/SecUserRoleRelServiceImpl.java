package com.taro.service.sec.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.sec.SecUserRoleRelDao;
import com.taro.entity.sec.SecUserRoleRelEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.sec.SecUserRoleRelService;

/**
 *<p>Title:SecRoleServiceImpl.java</p>
 *<p>Description:用户角色关系表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@Service
public class SecUserRoleRelServiceImpl extends AbstractService<SecUserRoleRelEntity> implements SecUserRoleRelService{
	@Autowired
	private SecUserRoleRelDao secUserRoleRelDao;

    @Override
    protected SecUserRoleRelDao getDao () {
        return secUserRoleRelDao;
    }

    @Override
    public SecUserRoleRelEntity saveSecUserRoleRel (SecUserRoleRelEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        if (StringUtils.isBlank(model.getRole_pid())) {
            throw new BusinessException("角色id不能为空");
        }
        if (StringUtils.isBlank(model.getUser_pid())) {
            throw new BusinessException("用户id不能为空");
        }

        // 删除该角色下之前的用户
        getDao().deleteRoleRel(model);
        
        SecUserRoleRelEntity saveBean = null;
        String[] userIdArr = model.getUser_pid().split(",");
        for(String user_pid : userIdArr) {
        	saveBean = new SecUserRoleRelEntity();
        	saveBean.setRole_pid(model.getRole_pid());
        	saveBean.setUser_pid(user_pid);
        	super.save(saveBean);
        }
        
        return model;
    }

}