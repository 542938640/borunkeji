package com.taro.dao.market;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.market.OrderExtEntity;

/**
 *<p>Title:OrderExtDao.java</p>
 *<p>Description:订单扩展表 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-29 16:25:37
 */
public interface OrderExtDao extends AbstractDao<OrderExtEntity> {

    List<OrderExtEntity> listOrder(Map<String, Object> parameter);

	Long listCount(Map<String, Object> parameter);

    List<OrderExtEntity> listHomeNum(Map<String, Object> parameter);

    Integer listHomeSumNum(Map<String, Object> parameter);
    
    Integer listHomeSumPrizeNum(Map<String, Object> parameter);
    
    List<OrderExtEntity> listAppHomeNum(Map<String, Object> parameter);
    
    List<OrderExtEntity> listAppHomeDaysNum(Map<String, Object> parameter);
}