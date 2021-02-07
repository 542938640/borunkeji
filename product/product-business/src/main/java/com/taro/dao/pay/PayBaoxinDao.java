package com.taro.dao.pay;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pay.PayBaoxinEntity;

/**
 *<p>Title:PayBaoxinDao.java</p>
 *<p>Description: Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 23:23:38
 */
public interface PayBaoxinDao extends AbstractDao<PayBaoxinEntity> {

    List<PayBaoxinEntity> listPayBaoxin(Map<String, Object> parameter);

}