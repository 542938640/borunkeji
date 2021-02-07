package com.taro.dao.device;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.device.DeviceExtEntity;

/**
 *<p>Title:DeviceExtDao.java</p>
 *<p>Description:设备信息扩展 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:36
 */
public interface DeviceExtDao extends AbstractDao<DeviceExtEntity> {

    List<DeviceExtEntity> listSelectDevice(Map<String, Object> parameter);
    
    List<DeviceExtEntity> listDevice(Map<String, Object> parameter);

    DeviceExtEntity getDeviceExt(String id);
    
}