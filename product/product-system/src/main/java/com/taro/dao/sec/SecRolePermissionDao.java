package com.taro.dao.sec;

import com.taro.dao.AbstractDao;
import com.taro.entity.sec.SecRolePermissionEntity;

/**
 *<p>Title:SecRolePermissionDao.java</p>
 *<p>Description:角色权限表Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
public interface SecRolePermissionDao extends AbstractDao<SecRolePermissionEntity> {

	int deleteRolePerm(SecRolePermissionEntity model);
}