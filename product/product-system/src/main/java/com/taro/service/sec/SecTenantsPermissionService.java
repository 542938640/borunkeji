package com.taro.service.sec;

import com.taro.entity.sec.SecTenantsPermissionEntity;
import com.taro.service.IService;

/**
 *<p>Title:SecTenantsPermissionService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-11 23:14:15
 */
public interface SecTenantsPermissionService extends IService<SecTenantsPermissionEntity> {
	
	SecTenantsPermissionEntity saveSecTenantsPermission(SecTenantsPermissionEntity model);
	
}