package com.taro.dao.market;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.market.MarketLuckDrawPrizeEntity;

/**
 *<p>Title:MarketLuckDrawPrizeDao.java</p>
 *<p>Description:幸运抽奖奖品 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:07
 */
public interface MarketLuckDrawPrizeDao extends AbstractDao<MarketLuckDrawPrizeEntity> {

    List<MarketLuckDrawPrizeEntity> listAndLock(Map<String, Object> parameter);

}