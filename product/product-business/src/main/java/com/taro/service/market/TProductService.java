package com.taro.service.market;

import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.market.TProductEntity;
import com.taro.service.IService;

/**
 *<p>Title:TProductService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:17:41
 */
public interface TProductService extends IService<TProductEntity> {
	
	TProductEntity saveTProduct(TProductEntity model);

    Page<TProductEntity> listProduct(int pageNum, int pageSize, Map<String, Object> parameter);
	
	TProductEntity saveTProductEnable(TProductEntity model);
	
}