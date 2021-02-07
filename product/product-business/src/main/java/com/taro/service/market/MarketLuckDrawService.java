package com.taro.service.market;

import com.taro.entity.market.MarketLuckDrawEntity;
import com.taro.service.IService;

/**
 *<p>Title:MarketLuckDrawService.java</p>
 *<p>Description:幸运抽奖 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 10:06:13
 */
public interface MarketLuckDrawService extends IService<MarketLuckDrawEntity> {
	
	MarketLuckDrawEntity saveMarketLuckDraw(MarketLuckDrawEntity model);
	
	MarketLuckDrawEntity saveMarketLuckDrawPrize(MarketLuckDrawEntity model);

	MarketLuckDrawEntity saveMarketLuckDrawRule(MarketLuckDrawEntity model);
	
	MarketLuckDrawEntity goOnline(String id);

	MarketLuckDrawEntity getRunByDevice(String device_did);

	MarketLuckDrawEntity getMarketLuckDraw(String id);

	MarketLuckDrawEntity getAndLock(String id);
}