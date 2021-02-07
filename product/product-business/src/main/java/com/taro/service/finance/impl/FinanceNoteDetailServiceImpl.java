package com.taro.service.finance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.finance.FinanceNoteDetailDao;
import com.taro.entity.finance.FinanceNoteDetailEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.finance.FinanceNoteDetailService;

/**
 *<p>Title:FinanceNoteDetailServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-02-05 13:48:14
 */
@Service
public class FinanceNoteDetailServiceImpl extends AbstractService<FinanceNoteDetailEntity> implements FinanceNoteDetailService {
	
	@Autowired
	private FinanceNoteDetailDao financeNoteDetailDao;
	
    @Override
    protected FinanceNoteDetailDao getDao() {
        return financeNoteDetailDao;
    }
    
    @Override
    public FinanceNoteDetailEntity saveFinanceNoteDetail (FinanceNoteDetailEntity model) {
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
    public FinanceNoteDetailEntity saveNum(String tenants_pid, String busi_type) {
    	FinanceNoteDetailEntity model = new FinanceNoteDetailEntity();
    	if(StringUtils.isNotBlank(tenants_pid) && StringUtils.isNotBlank(busi_type)) {
    		Map<String, Object> queryMap = new HashMap<>();
    		queryMap.put("sysFlag", "1");
    		queryMap.put("tenants_pid", tenants_pid);
    		queryMap.put("busi_type", busi_type);
    		List<FinanceNoteDetailEntity> queryList = super.list(queryMap);
    		if(CollectionUtils.isNotEmpty(queryList)) {
    			model.setId(queryList.get(0).getId());
    			getDao().updateNum(model);
    		}else {
    			model.setTenants_pid(tenants_pid);
    			model.setBusi_type(busi_type);
    			model.setNum(1);
    		}
    		
    	}
    	return model;
    }
}