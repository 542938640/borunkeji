package com.taro.service.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taro.common.Page;
import com.taro.dao.pay.PayBaoxinDao;
import com.taro.entity.pay.PayBaoxinEntity;
import com.taro.entity.pay.TPaySettingEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pay.PayBaoxinService;
import com.taro.service.pay.TPaySettingService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:PayBaoxinServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 23:23:38
 */
@Service
public class PayBaoxinServiceImpl extends AbstractService<PayBaoxinEntity> implements PayBaoxinService {
	
	@Autowired
	private PayBaoxinDao payBaoxinDao;
	
    @Override
    protected PayBaoxinDao getDao() {
        return payBaoxinDao;
    }

	@Autowired
	private TPaySettingService tPaySettingService;
	
    @Override
    public Page<PayBaoxinEntity> listPayBaoxin (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<PayBaoxinEntity> reuslt = getDao().listPayBaoxin(parameter);
        Page<PayBaoxinEntity> page = new Page<>(reuslt);
        return page;
    }
    
    @Override
    public PayBaoxinEntity savePayBaoxin (PayBaoxinEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	if(MyStringUtil.isBlank(model.getTenants_pid())) {
            throw new BusinessException("所属商户不能为空!");
    	}
    	
    	Map<String, Object> queryMap = new HashMap<>();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("tenants_pid", model.getTenants_pid());
    	List<PayBaoxinEntity> queryList = super.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("商户[" + model.getTenants_name() + "]已存在,请重新选择!");
    	}

    	TPaySettingEntity tPaySettingEntity = null;
    	queryMap.clear();
        queryMap.put("pscreatecompany", model.getTenants_pid());
        List<TPaySettingEntity> queryPaySettingList = tPaySettingService.list(queryMap);
        if(CollectionUtils.isEmpty(queryPaySettingList)) {
        	tPaySettingEntity = new TPaySettingEntity();
        }else {
        	tPaySettingEntity = queryPaySettingList.get(0);
        }
        
    	tPaySettingEntity.setWxmch_id(model.getWx_mchid());
    	tPaySettingEntity.setWxsubmch_id(model.getWx_sub_mchid());
    	tPaySettingEntity.setWxappid(model.getWx_appid());
    	tPaySettingEntity.setWxsubappid(model.getWx_sup_appid());
    	tPaySettingEntity.setWxappkey(model.getWx_appkey());
    	tPaySettingEntity.setAppsecret(model.getWx_appsecret());
    	tPaySettingEntity.setSubappsecret(model.getWx_sup_appsecret());
    	tPaySettingEntity.setAlipid(model.getZfb_pid());
    	tPaySettingEntity.setPscreatecompany(model.getTenants_pid()); // 所属商户
    	
    	if(MyStringUtil.isBlank(tPaySettingEntity.getId())) {
    		tPaySettingService.save(tPaySettingEntity);
    	}else {
    		tPaySettingService.update(tPaySettingEntity);
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }
    
}