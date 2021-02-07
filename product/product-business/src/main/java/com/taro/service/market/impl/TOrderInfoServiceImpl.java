package com.taro.service.market.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.market.TOrderInfoDao;
import com.taro.entity.market.TOrderInfoEntity;
import com.taro.service.AbstractService;
import com.taro.service.market.TOrderInfoService;

/**
 *<p>Title:TOrderInfoServiceImpl.java</p>
 *<p>Description:订单信息表 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-28 20:33:44
 */
@Service
public class TOrderInfoServiceImpl extends AbstractService<TOrderInfoEntity> implements TOrderInfoService {
	
	@Autowired
	private TOrderInfoDao tOrderInfoDao;
	
    @Override
    protected TOrderInfoDao getDao() {
        return tOrderInfoDao;
    }
    
    @Override
    public TOrderInfoEntity saveTOrderInfo (TOrderInfoEntity model) {
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