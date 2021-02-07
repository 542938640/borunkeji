package com.taro.service.pay;

import java.util.List;
import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.pay.PayUnionpayMerEntity;
import com.taro.service.IService;

/**
 *<p>Title:PayUnionpayMerService.java</p>
 *<p>Description:银联商户号 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:52
 */
public interface PayUnionpayMerService extends IService<PayUnionpayMerEntity> {

    List<PayUnionpayMerEntity> listPayUnionpayMer(Map<String, Object> parameter);
    
    Page<PayUnionpayMerEntity> listPayUnionpayMer(int pageNum, int pageSize, Map<String, Object> parameter);
    
	PayUnionpayMerEntity savePayUnionpayMer(PayUnionpayMerEntity model);

	void deleteTenants(String unionpay_pid);

    int deleteAllByUnionpayPid(String ids);
    
    List<PayUnionpayMerEntity> listParentMer(Map<String, Object> parameter);
}