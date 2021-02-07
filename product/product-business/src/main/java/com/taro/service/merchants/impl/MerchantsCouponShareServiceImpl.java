package com.taro.service.merchants.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.merchants.MerchantsCouponShareDao;
import com.taro.entity.merchants.MerchantsCouponShareEntity;
import com.taro.service.AbstractService;
import com.taro.service.merchants.MerchantsCouponShareService;

/**
 *<p>Title:MerchantsCouponShareServiceImpl.java</p>
 *<p>Description:商户优惠券共享 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-12-17 19:22:13
 */
@Service
public class MerchantsCouponShareServiceImpl extends AbstractService<MerchantsCouponShareEntity> implements MerchantsCouponShareService {
	
	@Autowired
	private MerchantsCouponShareDao merchantsCouponShareDao;
	
    @Override
    protected MerchantsCouponShareDao getDao() {
        return merchantsCouponShareDao;
    }
    
    @Override
    public MerchantsCouponShareEntity saveMerchantsCouponShare (MerchantsCouponShareEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }
    
}