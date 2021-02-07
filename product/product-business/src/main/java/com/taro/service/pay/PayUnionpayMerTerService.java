package com.taro.service.pay;

import com.taro.entity.pay.PayUnionpayMerTerEntity;
import com.taro.service.IService;

/**
 *<p>Title:PayUnionpayMerTerService.java</p>
 *<p>Description:银联商户终端号 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:16:04
 */
public interface PayUnionpayMerTerService extends IService<PayUnionpayMerTerEntity> {
	
	PayUnionpayMerTerEntity savePayUnionpayMerTer(PayUnionpayMerTerEntity model);

	void deleteTenants(String unionpay_pid);

    int deleteAllByUnionpayPid(String ids);

    void updateTenants(String unionpay_mer_pid, String tenants_pid);
}