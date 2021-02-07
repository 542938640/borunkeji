package com.taro.service.device;

import com.taro.entity.device.DeviceOutletsEntity;
import com.taro.service.IService;

/**
 *<p>Title:DeviceOutletsService.java</p>
 *<p>Description:设备网点信息 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:18
 */
public interface DeviceOutletsService extends IService<DeviceOutletsEntity> {
	
	DeviceOutletsEntity saveDeviceOutlets(DeviceOutletsEntity model);
	
}