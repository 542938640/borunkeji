package com.taro.service.advert.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.advert.AdvertEndDetailDao;
import com.taro.entity.advert.AdvertEndDetailEntity;
import com.taro.service.AbstractService;
import com.taro.service.advert.AdvertEndDetailService;

/**
 *<p>Title:AdvertEndDetailServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-05 19:59:54
 */
@Service
public class AdvertEndDetailServiceImpl extends AbstractService<AdvertEndDetailEntity> implements AdvertEndDetailService {
	
	@Autowired
	private AdvertEndDetailDao advertEndDetailDao;
	
    @Override
    protected AdvertEndDetailDao getDao() {
        return advertEndDetailDao;
    }
    
    @Override
    public AdvertEndDetailEntity saveAdvertEndDetail (AdvertEndDetailEntity model) {
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