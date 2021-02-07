package com.taro.service.pay;

import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.pay.PayRefundEntity;
import com.taro.service.IService;

/**
 *<p>Title:PayRefundService.java</p>
 *<p>Description:退款申请 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-27 14:34:57
 */
public interface PayRefundService extends IService<PayRefundEntity> {

    Page<PayRefundEntity> listPayRefund(int pageNum, int pageSize, Map<String, Object> parameter);
    
	PayRefundEntity savePayRefund(PayRefundEntity model);
	
	PayRefundEntity startAutoWorkFlow(PayRefundEntity model);
	
	PayRefundEntity checkAutoWorkFlow(PayRefundEntity model);

	PayRefundEntity saveStatus(PayRefundEntity model);
	
}