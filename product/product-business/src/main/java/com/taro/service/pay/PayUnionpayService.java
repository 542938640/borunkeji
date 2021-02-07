package com.taro.service.pay;

import com.taro.entity.pay.PayUnionpayEntity;
import com.taro.service.IService;

/**
 *<p>Title:PayUnionpayService.java</p>
 *<p>Description:银联支付组 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:33
 */
public interface PayUnionpayService extends IService<PayUnionpayEntity> {
	
	PayUnionpayEntity savePayUnionpay(PayUnionpayEntity model);
	
}