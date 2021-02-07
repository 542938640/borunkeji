package com.taro.service.market;

import java.util.List;
import java.util.Map;

import com.taro.entity.market.TProductTypeEntity;
import com.taro.service.IService;

/**
 *<p>Title:TProductTypeService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:18:31
 */
public interface TProductTypeService extends IService<TProductTypeEntity> {

    List<TProductTypeEntity> listTree(Map<String, Object> parameter);
    
	TProductTypeEntity saveTProductType(TProductTypeEntity model);
	
}