package com.taro.service.market.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.market.MarketPayActPrizeDao;
import com.taro.entity.market.MarketPayActPrizeEntity;
import com.taro.service.AbstractService;
import com.taro.service.market.MarketPayActPrizeService;

/**
 *<p>Title:MarketPayActPrizeServiceImpl.java</p>
 *<p>Description:支付活动奖品 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:48
 */
@Service
public class MarketPayActPrizeServiceImpl extends AbstractService<MarketPayActPrizeEntity> implements MarketPayActPrizeService {
	
	@Autowired
	private MarketPayActPrizeDao marketPayActPrizeDao;
	
    @Override
    protected MarketPayActPrizeDao getDao() {
        return marketPayActPrizeDao;
    }
    
    @Override
    public MarketPayActPrizeEntity saveMarketPayActPrize (MarketPayActPrizeEntity model) {
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