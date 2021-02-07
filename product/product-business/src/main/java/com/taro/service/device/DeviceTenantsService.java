package com.taro.service.device;

import com.taro.entity.device.DeviceTenantsEntity;
import com.taro.service.IService;

/**
 *<p>Title:DeviceTenantsService.java</p>
 *<p>Description:设备历史机构 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-10 16:48:16
 */
public interface DeviceTenantsService extends IService<DeviceTenantsEntity> {
	
	DeviceTenantsEntity saveDeviceTenants(DeviceTenantsEntity model);

	DeviceTenantsEntity saveByTenants(String device_did, String tenants_pid);
	
}