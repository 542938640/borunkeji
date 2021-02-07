package com.taro.service.device;

import java.util.Map;

import com.taro.entity.device.DeviceInfoEntity;
import com.taro.service.IService;

/**
 *<p>Title:DeviceInfoService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-17 11:47:03
 */
public interface DeviceInfoService extends IService<DeviceInfoEntity> {
	
	DeviceInfoEntity saveDeviceInfo(DeviceInfoEntity model);

	DeviceInfoEntity listHomeNum(Map<String, Object> parameter);
    
}