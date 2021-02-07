package com.taro.dao.sec;

import com.taro.dao.AbstractDao;
import com.taro.entity.sec.SecUserRoleRelEntity;

/**
 *<p>Title:SecuserRoleRelDao.java</p>
 *<p>Description:用户角色关系表Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public interface SecUserRoleRelDao extends AbstractDao<SecUserRoleRelEntity> {

	int deleteRoleRel(SecUserRoleRelEntity model);
}