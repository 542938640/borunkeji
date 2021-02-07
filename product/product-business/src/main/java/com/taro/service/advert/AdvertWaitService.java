package com.taro.service.advert;

import com.taro.entity.advert.AdvertWaitEntity;
import com.taro.service.IService;

/**
 *<p>Title:AdvertWaitService.java</p>
 *<p>Description:待机广告 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:22:08
 */
public interface AdvertWaitService extends IService<AdvertWaitEntity> {
	
	AdvertWaitEntity saveAdvertWait(AdvertWaitEntity model);

	AdvertWaitEntity publish(String id);
	
}