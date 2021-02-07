package com.taro.service.device.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.device.DeviceTenantsDao;
import com.taro.entity.device.DeviceTenantsEntity;
import com.taro.entity.sec.SecTenantsEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.device.DeviceTenantsService;
import com.taro.service.sec.SecTenantsService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:DeviceTenantsServiceImpl.java</p>
 *<p>Description:设备历史机构 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-10 16:48:16
 */
@Service
public class DeviceTenantsServiceImpl extends AbstractService<DeviceTenantsEntity> implements DeviceTenantsService {
	
	@Autowired
	private DeviceTenantsDao deviceTenantsDao;
	
    @Override
    protected DeviceTenantsDao getDao() {
        return deviceTenantsDao;
    }

	@Autowired
	private SecTenantsService secTenantsService;
	
    @Override
    public DeviceTenantsEntity saveDeviceTenants (DeviceTenantsEntity model) {
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
    public DeviceTenantsEntity saveByTenants(String device_did, String tenants_pid) {
    	if(MyStringUtil.isBlank(tenants_pid)) {
            throw new BusinessException("机构id不能为空!");
    	}else if(MyStringUtil.isBlank(device_did)) {
            throw new BusinessException("设备did不能为空!");
    	}
    	
    	DeviceTenantsEntity saveModel = new DeviceTenantsEntity();
    	saveModel.setDevice_did(device_did);
    	
    	SecTenantsEntity orgModel = secTenantsService.get(tenants_pid);
    	if(null == orgModel) {
            throw new BusinessException("机构id查询为空!");
    	}
    	
    	saveModel = loopOrg(saveModel, orgModel);
    	super.save(saveModel);
    	
    	return null;
    }
    
    private DeviceTenantsEntity loopOrg(DeviceTenantsEntity model, SecTenantsEntity orgModel) {
    	
    	if(null != orgModel) {
    		switch (orgModel.getLevel()) {
			case 1:
				model.setHead_office_pid(orgModel.getId());
				model.setHead_office_name(orgModel.getName());
				break;
			case 2:
				model.setProvincial_pid(orgModel.getId());
				model.setProvincial_name(orgModel.getName());
				break;
			case 3:
				model.setCity_pid(orgModel.getId());
				model.setCity_name(orgModel.getName());
				break;
			case 4:
				model.setBranch_pid(orgModel.getId());
				model.setBranch_name(orgModel.getName());
				break;
			case 5:
				model.setOutlets_pid(orgModel.getId());
				model.setOutlets_name(orgModel.getName());
				break;
			default:
				break;
			}
    		if(MyStringUtil.isNotBlank(orgModel.getParent_pid())) {
    	    	SecTenantsEntity parentModel = secTenantsService.get(orgModel.getParent_pid());
    	    	if(null != parentModel) {
        			model = this.loopOrg(model, parentModel);
    	    	}
    		}
    	}
    	
    	return model;
    }
    
}