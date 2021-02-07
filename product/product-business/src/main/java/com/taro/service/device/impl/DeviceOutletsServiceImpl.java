package com.taro.service.device.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.taro.exception.BusinessException;

import com.taro.dao.device.DeviceOutletsDao;
import com.taro.entity.device.DeviceOutletsEntity;
import com.taro.service.AbstractService;
import com.taro.service.device.DeviceOutletsService;

/**
 *<p>Title:DeviceOutletsServiceImpl.java</p>
 *<p>Description:设备网点信息 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:18
 */
@Service
public class DeviceOutletsServiceImpl extends AbstractService<DeviceOutletsEntity> implements DeviceOutletsService {
	
	@Autowired
	private DeviceOutletsDao deviceOutletsDao;
	
    @Override
    protected DeviceOutletsDao getDao() {
        return deviceOutletsDao;
    }
    
    @Override
    public DeviceOutletsEntity saveDeviceOutlets (DeviceOutletsEntity model) {
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