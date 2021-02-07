package com.taro.dao.pay;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pay.PayUnionpayMerEntity;

/**
 *<p>Title:PayUnionpayMerDao.java</p>
 *<p>Description:银联商户号 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:52
 */
public interface PayUnionpayMerDao extends AbstractDao<PayUnionpayMerEntity> {

    List<PayUnionpayMerEntity> listPayUnionpayMer(Map<String, Object> parameter);
    
    int deleteTenants(String unionpay_pid);

	int deleteAllByUnionpayPid(Map<String, Object> parameter);

    List<PayUnionpayMerEntity> listParentMer(Map<String, Object> parameter);
}