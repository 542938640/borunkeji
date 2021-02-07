package com.taro.service.market;

import com.taro.entity.market.TOrderInfoEntity;
import com.taro.service.IService;

/**
 *<p>Title:TOrderInfoService.java</p>
 *<p>Description:订单信息表 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-28 20:33:44
 */
public interface TOrderInfoService extends IService<TOrderInfoEntity> {
	
	TOrderInfoEntity saveTOrderInfo(TOrderInfoEntity model);
	
}