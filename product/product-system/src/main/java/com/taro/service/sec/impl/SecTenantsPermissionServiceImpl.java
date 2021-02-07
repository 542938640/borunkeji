package com.taro.service.sec.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.sec.SecTenantsPermissionDao;
import com.taro.entity.sec.SecTenantsPermissionEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.sec.SecTenantsPermissionService;

/**
 *<p>Title:SecTenantsPermissionServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-11 23:14:15
 */
@Service
public class SecTenantsPermissionServiceImpl extends AbstractService<SecTenantsPermissionEntity> implements SecTenantsPermissionService {
	
	@Autowired
	private SecTenantsPermissionDao secTenantsPermissionDao;
	
    @Override
    protected SecTenantsPermissionDao getDao() {
        return secTenantsPermissionDao;
    }

    @Override
    public SecTenantsPermissionEntity saveSecTenantsPermission (SecTenantsPermissionEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        if (StringUtils.isBlank(model.getTenants_pid())) {
            throw new BusinessException("角色id不能为空");
        }
        if (CollectionUtils.isEmpty(model.getResourceList())) {
            throw new BusinessException("资源不能为空");
        }

        // 删除该角色下之前的用户
        getDao().deleteTenantsPerm(model);

        SecTenantsPermissionEntity saveBean = null;
        for(SecTenantsPermissionEntity resource : model.getResourceList()) {
        	saveBean = new SecTenantsPermissionEntity();
        	saveBean.setTenants_pid(model.getTenants_pid());
        	saveBean.setResource_pid(resource.getResource_pid());
        	saveBean.setType(model.getType());
        	super.save(saveBean);
        }
        
        return model;
    }
}