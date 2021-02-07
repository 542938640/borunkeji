package com.taro.service.advert;

import com.taro.entity.advert.AdvertActivitiesEntity;
import com.taro.service.IService;

/**
 *<p>Title:AdvertActivitiesService.java</p>
 *<p>Description:活动广告 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:23:49
 */
public interface AdvertActivitiesService extends IService<AdvertActivitiesEntity> {
	
	AdvertActivitiesEntity saveAdvertActivities(AdvertActivitiesEntity model);

	AdvertActivitiesEntity publish(String id);
	
}