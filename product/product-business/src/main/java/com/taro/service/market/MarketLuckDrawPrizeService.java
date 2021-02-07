package com.taro.service.market;

import java.util.List;
import java.util.Map;

import com.taro.entity.market.MarketLuckDrawPrizeEntity;
import com.taro.service.IService;

/**
 *<p>Title:MarketLuckDrawPrizeService.java</p>
 *<p>Description:幸运抽奖奖品 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:07
 */
public interface MarketLuckDrawPrizeService extends IService<MarketLuckDrawPrizeEntity> {
	
	MarketLuckDrawPrizeEntity saveMarketLuckDrawPrize(MarketLuckDrawPrizeEntity model);

    List<MarketLuckDrawPrizeEntity> listAndLock(Map<String, Object> parameter);
	
}