package com.taro.dao.sec;

import com.taro.dao.AbstractDao;
import com.taro.entity.sec.SecTenantsPermissionEntity;

/**
 *<p>Title:SecTenantsPermissionDao.java</p>
 *<p>Description: Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-11 23:14:15
 */
public interface SecTenantsPermissionDao extends AbstractDao<SecTenantsPermissionEntity> {

	int deleteTenantsPerm(SecTenantsPermissionEntity model);
}