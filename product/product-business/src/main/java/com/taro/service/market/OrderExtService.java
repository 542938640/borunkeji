package com.taro.service.market;

import java.util.List;
import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.market.OrderExtEntity;
import com.taro.service.IService;

/**
 *<p>Title:OrderExtService.java</p>
 *<p>Description:订单扩展表 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-29 16:25:37
 */
public interface OrderExtService extends IService<OrderExtEntity> {
	
	OrderExtEntity saveOrderExt(OrderExtEntity model);

	List<OrderExtEntity> listOrder(Map<String, Object> parameter);
	
	Page<OrderExtEntity> listOrder(int pageNum, int pageSize, Map<String, Object> parameter);

	Long listCount(Map<String, Object> parameter);

	OrderExtEntity listHomeNum(Map<String, Object> parameter);

    List<OrderExtEntity> listAppHomeNum(Map<String, Object> param);

    List<OrderExtEntity> listAppHomeDaysNum(Map<String, Object> param);
    
}