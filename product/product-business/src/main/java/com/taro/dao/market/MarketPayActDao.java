package com.taro.dao.market;

import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.market.MarketPayActEntity;

/**
 *<p>Title:MarketPayActDao.java</p>
 *<p>Description:支付活动 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:32
 */
public interface MarketPayActDao extends AbstractDao<MarketPayActEntity> {

	Long listCount(Map<String, Object> parameter);
    
}