package com.taro.service.market;

import com.taro.entity.market.OwnProductsEntity;
import com.taro.service.IService;

/**
 *<p>Title:OwnProductsService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-26 23:48:00
 */
public interface OwnProductsService extends IService<OwnProductsEntity> {
	
	OwnProductsEntity saveOwnProducts(OwnProductsEntity model);
	
}