package com.taro.service.market.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.market.MarketLuckDrawPrizeDao;
import com.taro.entity.market.MarketLuckDrawPrizeEntity;
import com.taro.service.AbstractService;
import com.taro.service.market.MarketLuckDrawPrizeService;

/**
 *<p>Title:MarketLuckDrawPrizeServiceImpl.java</p>
 *<p>Description:幸运抽奖奖品 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:07
 */
@Service
public class MarketLuckDrawPrizeServiceImpl extends AbstractService<MarketLuckDrawPrizeEntity> implements MarketLuckDrawPrizeService {
	
	@Autowired
	private MarketLuckDrawPrizeDao marketLuckDrawPrizeDao;
	
    @Override
    protected MarketLuckDrawPrizeDao getDao() {
        return marketLuckDrawPrizeDao;
    }
    
    @Override
    public MarketLuckDrawPrizeEntity saveMarketLuckDrawPrize (MarketLuckDrawPrizeEntity model) {
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

    @Override
    public List<MarketLuckDrawPrizeEntity> listAndLock(Map<String, Object> parameter) {
        return getDao().listAndLock(parameter);
    }
}