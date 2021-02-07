package com.taro.service.device.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.device.DeviceInfoDao;
import com.taro.entity.device.DeviceInfoEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.device.DeviceInfoService;

/**
 *<p>Title:DeviceInfoServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-17 11:47:03
 */
@Service
public class DeviceInfoServiceImpl extends AbstractService<DeviceInfoEntity> implements DeviceInfoService {
	
	@Autowired
	private DeviceInfoDao deviceInfoDao;
	
    @Override
    protected DeviceInfoDao getDao() {
        return deviceInfoDao;
    }

    @Override
    public DeviceInfoEntity listHomeNum(Map<String, Object> parameter) {
    	DeviceInfoEntity model = new DeviceInfoEntity();
    	model.setOrg_num(getDao().listHomeOrgNum(parameter));
    	model.setDinolineList(getDao().listHomeNum(parameter));
        return model;
    }
    
    @Override
    public DeviceInfoEntity saveDeviceInfo (DeviceInfoEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	model.setId("060B5F25-1A65-42DA-8D0F-1E04E9F39030");
    	model.setAdvertisingswitch(0);
    	model.setAlicloudcode(1);
    	model.setCity("湘潭");
    	model.setCountry("湖南");
    	model.setEnable(2);
    	model.setDiaddress("雨湖区");
    	model.setDialias("Dialias");
    	model.setDiassetsid("Diassetsid");
    	model.setDibelonguser("Dibelonguser");
    	model.setDibusinessid("Dibusinessid");
    	model.setDibuytime(new Date());
    	model.setDigroup("Digroup");
    	model.setDiinsidetemp("Diinsidetemp");
    	model.setDillatitude(3D);
    	model.setDilongitude(4D);
    	model.setDinoline(5);
    	model.setDipwd("Dipwd");
    	model.setDisceneid("Disceneid");
    	model.setDisize(6);
    	model.setDiwork(7);
    	model.setInternaladdress("Internaladdress");
    	model.setIscart(8);
    	model.setMaxtemperature(9D);
    	model.setMibuyprice(10);
    	model.setMicclimit(11);
    	model.setMicvs(12);
    	model.setMiisdeploy(13);
    	model.setMintemperature(14D);
    	model.setMiout(15);
    	model.setMiouter_temp("Miouter_temp");
    	model.setMipoweroff("Mipoweroff");
    	model.setMiqrctype(16);
    	model.setMiserialnumber(17);
    	model.setMisizetype(18);
    	model.setPosturl("Posturl");
    	model.setProvince("Province");
    	model.setPureelectron(19);
    	model.setRefundcount(20);
    	model.setRefundverify(21);
    	model.setScheduledstate(22);
    	model.setScheduledtime("Scheduledtime");
    	model.setSpellluck(23);
    	model.setThreetiercity("Threetiercity");
    	model.setVersion("Version");
    	model.setWxsinglediscount(24);
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }
    
}