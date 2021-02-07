package com.taro.dao.device;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.device.DeviceInfoEntity;

/**
 *<p>Title:DeviceInfoDao.java</p>
 *<p>Description: Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-17 11:47:03
 */
public interface DeviceInfoDao extends AbstractDao<DeviceInfoEntity> {

    List<DeviceInfoEntity> listHomeNum(Map<String, Object> parameter);

    Long listHomeOrgNum(Map<String, Object> parameter);
    
}