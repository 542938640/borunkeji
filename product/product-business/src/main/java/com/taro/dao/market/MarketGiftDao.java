package com.taro.dao.market;

import com.taro.dao.AbstractDao;
import com.taro.entity.market.MarketGiftEntity;

/**
 *<p>Title:MarketGiftDao.java</p>
 *<p>Description:积分换礼 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:20:40
 */
public interface MarketGiftDao extends AbstractDao<MarketGiftEntity> {

	MarketGiftEntity getAndLock(String id);
    
}