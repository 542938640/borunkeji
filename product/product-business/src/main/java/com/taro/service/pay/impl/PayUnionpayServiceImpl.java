package com.taro.service.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.pay.PayUnionpayDao;
import com.taro.entity.pay.PayUnionpayEntity;
import com.taro.entity.pay.PayUnionpayMerEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pay.PayUnionpayMerService;
import com.taro.service.pay.PayUnionpayMerTerService;
import com.taro.service.pay.PayUnionpayService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:PayUnionpayServiceImpl.java</p>
 *<p>Description:银联支付组 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:33
 */
@Service
public class PayUnionpayServiceImpl extends AbstractService<PayUnionpayEntity> implements PayUnionpayService {
	
	@Autowired
	private PayUnionpayDao payUnionpayDao;
	
    @Override
    protected PayUnionpayDao getDao() {
        return payUnionpayDao;
    }
    
	@Autowired
	private PayUnionpayMerService payUnionpayMerService;

	@Autowired
	private PayUnionpayMerTerService payUnionpayMerTerService;
    
    @Override
    public PayUnionpayEntity savePayUnionpay (PayUnionpayEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	if(MyStringUtil.isBlank(model.getName())) {
            throw new BusinessException("支付组名称不能为空!");
    	}
    	if(MyStringUtil.isBlank(model.getTenants_pid())) {
            throw new BusinessException("支付组所属商户不能为空!");
    	}
    	
    	Map<String, Object> queryMap = new HashMap<>();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("nameEq", model.getName());
    	List<PayUnionpayEntity> queryList = super.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("名称[" + model.getName() + "]已存在,请重新编辑!");
    	}

    	queryMap.clear();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("tenants_pid", model.getTenants_pid());
    	queryList = super.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("商户[" + model.getTenants_name() + "]已绑定支付组[" + queryList.get(0).getName() + "],请重新选择!");
    	}

    	queryMap.clear();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("tenants_pid", model.getTenants_pid());
    	List<PayUnionpayMerEntity> queryMerList = payUnionpayMerService.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryMerList)) {
            throw new BusinessException("商户[" + model.getTenants_name() + "]已绑定商户号[" + queryMerList.get(0).getName() + "],请重新选择!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    		
    		// 增加一个默认的通用商户号
    		PayUnionpayMerEntity payUnionpayMer = new PayUnionpayMerEntity();
    		payUnionpayMer.setUnionpay_pid(model.getId());
    		payUnionpayMer.setIsCur("1");
    		payUnionpayMer.setName("通用商户号");
    		payUnionpayMerService.save(payUnionpayMer);
    		
    	}else {
    		PayUnionpayEntity oldModel = super.get(model.getId());
    		if(null == oldModel) {
                throw new BusinessException("查询对象错误!");
    		}
    		// 若支付组修改了租户,则清空商户号和终端号的租户
    		if(!oldModel.getTenants_pid().equals(model.getTenants_pid())) {
    			payUnionpayMerService.deleteTenants(model.getId());
    			payUnionpayMerTerService.deleteTenants(model.getId());
    		}
    		
    		super.update(model);
    	}
    	
        return model;
    }

    @Override
    public int deleteAll (String ids) {
    	int num = super.deleteAll(ids);
    	
    	if(StringUtils.isNotBlank(ids)) {
    		payUnionpayMerService.deleteAllByUnionpayPid(ids);
    		payUnionpayMerTerService.deleteAllByUnionpayPid(ids);
    	}
    	
        return num;
    }
}