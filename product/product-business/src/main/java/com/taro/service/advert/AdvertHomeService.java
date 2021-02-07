package com.taro.service.advert;

import com.taro.entity.advert.AdvertHomeEntity;
import com.taro.service.IService;

/**
 *<p>Title:AdvertHomeService.java</p>
 *<p>Description:首页广告 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:21:52
 */
public interface AdvertHomeService extends IService<AdvertHomeEntity> {
	
	AdvertHomeEntity saveAdvertHome(AdvertHomeEntity model);

	AdvertHomeEntity publish(String id);
	
}