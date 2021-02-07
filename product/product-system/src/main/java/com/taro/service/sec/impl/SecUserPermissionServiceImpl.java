package com.taro.service.sec.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.sec.SecUserPermissionDao;
import com.taro.entity.sec.SecUserPermissionEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.sec.SecUserPermissionService;

/**
 *<p>Title:SecUserPermissionServiceImpl.java</p>
 *<p>Description:用户权限表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
@Service
public class SecUserPermissionServiceImpl extends AbstractService<SecUserPermissionEntity> implements SecUserPermissionService{
	@Autowired
	private SecUserPermissionDao secUserPermissionDao;
    @Override
    protected SecUserPermissionDao getDao () {
        return secUserPermissionDao;
    }

    @Override
    public SecUserPermissionEntity saveSecUserPermission (SecUserPermissionEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        if (StringUtils.isBlank(model.getUser_pid())) {
            throw new BusinessException("用户id不能为空");
        }
        if (CollectionUtils.isEmpty(model.getResourceList())) {
            throw new BusinessException("资源不能为空");
        }

        // 删除该用户下之前的资源
        getDao().deleteUserPerm(model);
        
        SecUserPermissionEntity saveBean = null;
        for(SecUserPermissionEntity resource : model.getResourceList()) {
        	saveBean = new SecUserPermissionEntity();
        	saveBean.setUser_pid(model.getUser_pid());
        	saveBean.setResource_pid(resource.getResource_pid());
        	saveBean.setType(model.getType());
        	super.save(saveBean);
        }
        
        return model;
    }

}