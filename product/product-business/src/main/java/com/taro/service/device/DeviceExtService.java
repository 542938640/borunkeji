package com.taro.service.device;

import java.util.List;
import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.device.DeviceExtEntity;
import com.taro.service.IService;

/**
 *<p>Title:DeviceExtService.java</p>
 *<p>Description:设备信息扩展 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:36
 */
public interface DeviceExtService extends IService<DeviceExtEntity> {

    List<DeviceExtEntity> listSelectDevice(Map<String, Object> parameter);
    
	DeviceExtEntity saveDeviceExt(DeviceExtEntity model);

	DeviceExtEntity getDeviceExt(String id);
    
    Page<DeviceExtEntity> listDevice(int pageNum, int pageSize, Map<String, Object> parameter);

    DeviceExtEntity saveDeviceTenants(DeviceExtEntity model);

    DeviceExtEntity saveDeviceAccount(DeviceExtEntity model);

    DeviceExtEntity saveDeviceUnbundling(DeviceExtEntity model);

    DeviceExtEntity saveDeviceRecycle(DeviceExtEntity model);
}