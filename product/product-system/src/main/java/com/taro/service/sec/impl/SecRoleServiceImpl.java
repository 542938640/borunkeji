package com.taro.service.sec.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.sec.SecRoleDao;
import com.taro.entity.sec.SecRoleEntity;
import com.taro.service.AbstractService;
import com.taro.service.sec.SecRoleService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.SecurityMyUtils;

/**
 *<p>Title:SecRoleServiceImpl.java</p>
 *<p>Description:角色表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@Service
public class SecRoleServiceImpl extends AbstractService<SecRoleEntity> implements SecRoleService{
	@Autowired
	private SecRoleDao secRoleDao;

    @Override
    protected SecRoleDao getDao () {
        return secRoleDao;
    }

    @Override
    public List<SecRoleEntity> listTree(Map<String, Object> parameter) {
        List<SecRoleEntity> reuslt = getDao().list(parameter);
        return reuslt;
    }

    @Override
    public SecRoleEntity saveSecRole (SecRoleEntity model) {
    	
    	// 设置租户id
    	if(MyStringUtil.isBlank(model.getTenants_pid())) {
        	model.setTenants_pid(SecurityMyUtils.getTenantsId());
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }

}