package com.taro.service.pay.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.constants.Constant;
import com.taro.dao.pay.PayUnionpayMerTerDao;
import com.taro.entity.pay.PayUnionpayMerTerEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pay.PayUnionpayMerTerService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.Utility;

/**
 *<p>Title:PayUnionpayMerTerServiceImpl.java</p>
 *<p>Description:银联商户终端号 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:16:04
 */
@Service
public class PayUnionpayMerTerServiceImpl extends AbstractService<PayUnionpayMerTerEntity> implements PayUnionpayMerTerService {
	
	@Autowired
	private PayUnionpayMerTerDao payUnionpayMerTerDao;
	
    @Override
    protected PayUnionpayMerTerDao getDao() {
        return payUnionpayMerTerDao;
    }
    
    @Override
    public PayUnionpayMerTerEntity savePayUnionpayMerTer (PayUnionpayMerTerEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		String[] ter_number_arr = model.getTer_number().split(",");
    		if(null != ter_number_arr) {
    			PayUnionpayMerTerEntity saveModel = null;
    			for(String ter_number : ter_number_arr) {
    				saveModel = new PayUnionpayMerTerEntity();
    				saveModel.setTer_number(ter_number);
    				saveModel.setUnionpay_pid(model.getUnionpay_pid());
    				saveModel.setUnionpay_mer_pid(model.getUnionpay_mer_pid());
    				saveModel.setTenants_pid(model.getTenants_pid());
    	    		super.save(saveModel);
    			}
    		}
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }


    @Override
    public void deleteTenants(String unionpay_pid) {
        if (MyStringUtil.isNotBlank(unionpay_pid)) {
            getDao().deleteTenants(unionpay_pid);
        }
    }

    @Override
    public int deleteAllByUnionpayPid(String ids) {
        //校验参数
        Utility.raiseIfWrong(ids, "ids不能为空");

        String[] pids = ids.split(Constant.COMMA);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("ids", pids);
        return getDao().deleteAllByUnionpayPid(parameter);
    }
    
    @Override
    public void updateTenants (String unionpay_mer_pid, String tenants_pid) {
    	if(MyStringUtil.isNotBlank(unionpay_mer_pid) && MyStringUtil.isNotBlank(tenants_pid)) {
    		PayUnionpayMerTerEntity model = new PayUnionpayMerTerEntity();
    		model.setUnionpay_mer_pid(unionpay_mer_pid);
    		model.setTenants_pid(tenants_pid);
            getDao().updateTenants(model);
    	}
    }
}