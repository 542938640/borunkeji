package com.taro.service.device;

import java.util.List;
import java.util.Map;

import com.taro.entity.device.TSlotInfoEntity;
import com.taro.service.IService;

/**
 *<p>Title:TSlotInfoService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:18:59
 */
public interface TSlotInfoService extends IService<TSlotInfoEntity> {

    List<TSlotInfoEntity> listSlot(Map<String, Object> parameter);
    
	TSlotInfoEntity saveTSlotInfo(TSlotInfoEntity model);

    int updateProduct(TSlotInfoEntity model);

    int updateCapacity(TSlotInfoEntity model);

    int updatePrice(TSlotInfoEntity model);

    TSlotInfoEntity getDeviceSlot(String id);

	TSlotInfoEntity saveDeviceSlot(TSlotInfoEntity model);
	
	void updateSiDown(String did);
}