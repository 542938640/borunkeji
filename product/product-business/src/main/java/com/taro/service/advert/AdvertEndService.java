package com.taro.service.advert;

import com.taro.entity.advert.AdvertEndEntity;
import com.taro.service.IService;

/**
 *<p>Title:AdvertEndService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-04 10:14:01
 */
public interface AdvertEndService extends IService<AdvertEndEntity> {
	
	AdvertEndEntity saveAdvertEnd(AdvertEndEntity model);

	AdvertEndEntity getByDeviceDid(String device_did);

	AdvertEndEntity saveUseWait(AdvertEndEntity model);

	AdvertEndEntity getUseWaitByDeviceDid(String device_did);
	
}