package com.taro.dao.device;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.device.TSlotInfoEntity;

/**
 *<p>Title:TSlotInfoDao.java</p>
 *<p>Description: Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:18:59
 */
public interface TSlotInfoDao extends AbstractDao<TSlotInfoEntity> {

    List<TSlotInfoEntity> listSlot(Map<String, Object> parameter);
    
}