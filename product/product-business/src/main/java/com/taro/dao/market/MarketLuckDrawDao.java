package com.taro.dao.market;

import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.market.MarketLuckDrawEntity;

/**
 *<p>Title:MarketLuckDrawDao.java</p>
 *<p>Description:幸运抽奖 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:18:32
 */
public interface MarketLuckDrawDao extends AbstractDao<MarketLuckDrawEntity> {

	MarketLuckDrawEntity getAndLock(String id);
	
	Long listCount(Map<String, Object> parameter);

	Long listOrderCount(String id);
}