package com.taro.service.market;

import com.taro.entity.market.MarketPayActEntity;
import com.taro.service.IService;

/**
 *<p>Title:MarketPayActService.java</p>
 *<p>Description:支付活动 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:32
 */
public interface MarketPayActService extends IService<MarketPayActEntity> {

	MarketPayActEntity saveMarketPayAct(MarketPayActEntity model);
	
	MarketPayActEntity saveMarketPayActPrize(MarketPayActEntity model);

	MarketPayActEntity saveMarketPayActRule(MarketPayActEntity model);
	
	MarketPayActEntity goOnline(String id);

	MarketPayActEntity getRunByDevice(String device_did, String type);

	MarketPayActEntity getMarketPayAct(String id);
	
}