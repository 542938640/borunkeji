package com.taro.service.sec;

import com.taro.entity.sec.SecUserPermissionEntity;
import com.taro.service.IService;

/**
 *<p>Title:SecUserPermissionService.java</p>
 *<p>Description:用户权限表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
public interface SecUserPermissionService extends IService<SecUserPermissionEntity> {

	SecUserPermissionEntity saveSecUserPermission(SecUserPermissionEntity model);
}