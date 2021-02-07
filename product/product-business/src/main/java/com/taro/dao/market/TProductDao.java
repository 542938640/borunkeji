package com.taro.dao.market;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.market.TProductEntity;

/**
 *<p>Title:TProductDao.java</p>
 *<p>Description: Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:17:41
 */
public interface TProductDao extends AbstractDao<TProductEntity> {

    List<TProductEntity> listProduct(Map<String, Object> parameter);

}