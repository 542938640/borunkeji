package com.taro.dao.pay;

import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pay.PayUnionpayMerTerEntity;

/**
 *<p>Title:PayUnionpayMerTerDao.java</p>
 *<p>Description:银联商户终端号 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:16:04
 */
public interface PayUnionpayMerTerDao extends AbstractDao<PayUnionpayMerTerEntity> {

    int deleteTenants(String unionpay_pid);

	int deleteAllByUnionpayPid(Map<String, Object> parameter);
	
    int updateTenants(PayUnionpayMerTerEntity entity);

}