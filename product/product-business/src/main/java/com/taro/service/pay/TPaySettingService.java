package com.taro.service.pay;

import com.taro.entity.pay.TPaySettingEntity;
import com.taro.service.IService;

/**
 *<p>Title:TPaySettingService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:55:56
 */
public interface TPaySettingService extends IService<TPaySettingEntity> {
	
	TPaySettingEntity saveTPaySetting(TPaySettingEntity model);
	
}