package com.taro.service.merchants;

import com.taro.entity.merchants.MerchantsCouponShareEntity;
import com.taro.service.IService;

/**
 *<p>Title:MerchantsCouponShareService.java</p>
 *<p>Description:商户优惠券共享 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-12-17 19:22:13
 */
public interface MerchantsCouponShareService extends IService<MerchantsCouponShareEntity> {
	
	MerchantsCouponShareEntity saveMerchantsCouponShare(MerchantsCouponShareEntity model);
	
}