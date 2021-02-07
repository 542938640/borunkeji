package com.taro.service.sec.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.sec.SecRolePermissionDao;
import com.taro.entity.sec.SecRolePermissionEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.sec.SecRolePermissionService;

/**
 *<p>Title:SecRolePermissionServiceImpl.java</p>
 *<p>Description:角色权限表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
@Service
public class SecRolePermissionServiceImpl extends AbstractService<SecRolePermissionEntity> implements SecRolePermissionService{
	@Autowired
	private SecRolePermissionDao secRolePermissionDao;
    @Override
    protected SecRolePermissionDao getDao () {
        return secRolePermissionDao;
    }

    @Override
    public SecRolePermissionEntity saveSecRolePermission (SecRolePermissionEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        if (StringUtils.isBlank(model.getRole_pid())) {
            throw new BusinessException("角色id不能为空");
        }
        if (CollectionUtils.isEmpty(model.getResourceList())) {
            throw new BusinessException("资源不能为空");
        }

        // 删除该角色下之前的用户
        getDao().deleteRolePerm(model);

        SecRolePermissionEntity saveBean = null;
        for(SecRolePermissionEntity resource : model.getResourceList()) {
        	saveBean = new SecRolePermissionEntity();
        	saveBean.setRole_pid(model.getRole_pid());
        	saveBean.setResource_pid(resource.getResource_pid());
        	saveBean.setType(model.getType());
        	super.save(saveBean);
        }
        
        return model;
    }

}