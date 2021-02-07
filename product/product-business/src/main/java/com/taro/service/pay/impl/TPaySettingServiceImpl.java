package com.taro.service.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.pay.TPaySettingDao;
import com.taro.entity.pay.TPaySettingEntity;
import com.taro.service.AbstractService;
import com.taro.service.pay.TPaySettingService;

/**
 *<p>Title:TPaySettingServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:55:56
 */
@Service
public class TPaySettingServiceImpl extends AbstractService<TPaySettingEntity> implements TPaySettingService {
	
	@Autowired
	private TPaySettingDao tPaySettingDao;
	
    @Override
    protected TPaySettingDao getDao() {
        return tPaySettingDao;
    }
    
    @Override
    public TPaySettingEntity saveTPaySetting (TPaySettingEntity model) {
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