package com.taro.service.device.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.device.DeviceBusitypeDao;
import com.taro.entity.device.DeviceBusitypeEntity;
import com.taro.service.AbstractService;
import com.taro.service.device.DeviceBusitypeService;

/**
 *<p>Title:DeviceBusitypeServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-16 11:26:40
 */
@Service
public class DeviceBusitypeServiceImpl extends AbstractService<DeviceBusitypeEntity> implements DeviceBusitypeService {
	
	@Autowired
	private DeviceBusitypeDao deviceBusitypeDao;
	
    @Override
    protected DeviceBusitypeDao getDao() {
        return deviceBusitypeDao;
    }
    
    @Override
    public DeviceBusitypeEntity saveDeviceBusitype (DeviceBusitypeEntity model) {
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