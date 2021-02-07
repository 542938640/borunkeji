package com.taro.service.sec;

import com.taro.entity.sec.SecRolePermissionEntity;
import com.taro.service.IService;

/**
 *<p>Title:SecRolePermissionService.java</p>
 *<p>Description:角色权限表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
public interface SecRolePermissionService extends IService<SecRolePermissionEntity> {

	SecRolePermissionEntity saveSecRolePermission(SecRolePermissionEntity model);

}