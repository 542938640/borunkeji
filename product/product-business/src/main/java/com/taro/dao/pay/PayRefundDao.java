package com.taro.dao.pay;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pay.PayRefundEntity;

/**
 *<p>Title:PayRefundDao.java</p>
 *<p>Description:退款申请 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-27 14:34:57
 */
public interface PayRefundDao extends AbstractDao<PayRefundEntity> {

    List<PayRefundEntity> listPayRefund(Map<String, Object> parameter);
    
}