package com.taro.service.pay;

import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.pay.PayBaoxinEntity;
import com.taro.service.IService;

/**
 *<p>Title:PayBaoxinService.java</p>
 *<p>Description: Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 23:23:38
 */
public interface PayBaoxinService extends IService<PayBaoxinEntity> {
	
	PayBaoxinEntity savePayBaoxin(PayBaoxinEntity model);

    Page<PayBaoxinEntity> listPayBaoxin(int pageNum, int pageSize, Map<String, Object> parameter);
    
}