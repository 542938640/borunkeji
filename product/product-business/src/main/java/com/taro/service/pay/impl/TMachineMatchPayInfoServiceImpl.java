package com.taro.service.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.pay.TMachineMatchPayInfoDao;
import com.taro.entity.pay.TMachineMatchPayInfoEntity;
import com.taro.service.AbstractService;
import com.taro.service.pay.TMachineMatchPayInfoService;

/**
 *<p>Title:TMachineMatchPayInfoServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:56:19
 */
@Service
public class TMachineMatchPayInfoServiceImpl extends AbstractService<TMachineMatchPayInfoEntity> implements TMachineMatchPayInfoService {
	
	@Autowired
	private TMachineMatchPayInfoDao tMachineMatchPayInfoDao;
	
    @Override
    protected TMachineMatchPayInfoDao getDao() {
        return tMachineMatchPayInfoDao;
    }
    
    @Override
    public TMachineMatchPayInfoEntity saveTMachineMatchPayInfo (TMachineMatchPayInfoEntity model) {
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