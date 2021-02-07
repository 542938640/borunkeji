package com.taro.service.sec;

import com.taro.entity.sec.SecUserRoleRelEntity;
import com.taro.service.IService;

/**
 *<p>Title:SecRoleService.java</p>
 *<p>Description:用户角色关系表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public interface SecUserRoleRelService extends IService<SecUserRoleRelEntity> {
	SecUserRoleRelEntity saveSecUserRoleRel(SecUserRoleRelEntity model);
}