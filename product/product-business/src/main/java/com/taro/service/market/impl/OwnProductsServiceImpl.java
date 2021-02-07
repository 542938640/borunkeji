package com.taro.service.market.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.market.OwnProductsDao;
import com.taro.entity.market.OwnProductsEntity;
import com.taro.service.AbstractService;
import com.taro.service.market.OwnProductsService;

/**
 *<p>Title:OwnProductsServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-26 23:48:00
 */
@Service
public class OwnProductsServiceImpl extends AbstractService<OwnProductsEntity> implements OwnProductsService {
	
	@Autowired
	private OwnProductsDao ownProductsDao;
	
    @Override
    protected OwnProductsDao getDao() {
        return ownProductsDao;
    }
    
    @Override
    public OwnProductsEntity saveOwnProducts (OwnProductsEntity model) {
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