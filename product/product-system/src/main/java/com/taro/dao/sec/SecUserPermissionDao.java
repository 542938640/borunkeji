package com.taro.dao.sec;

import com.taro.dao.AbstractDao;
import com.taro.entity.sec.SecUserPermissionEntity;

/**
 *<p>Title:SecUserPermissionDao.java</p>
 *<p>Description:用户权限表Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
public interface SecUserPermissionDao extends AbstractDao<SecUserPermissionEntity> {

	int deleteUserPerm(SecUserPermissionEntity model);
}