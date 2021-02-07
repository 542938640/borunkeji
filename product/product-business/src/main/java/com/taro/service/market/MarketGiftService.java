package com.taro.service.market;

import com.taro.entity.market.MarketGiftEntity;
import com.taro.service.IService;

/**
 *<p>Title:MarketGiftService.java</p>
 *<p>Description:积分换礼 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:20:40
 */
public interface MarketGiftService extends IService<MarketGiftEntity> {
	
	MarketGiftEntity saveMarketGift(MarketGiftEntity model);
	
}