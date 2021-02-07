package com.taro.service.pay.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.dao.pay.PayUnionpayMerDao;
import com.taro.entity.pay.PayUnionpayEntity;
import com.taro.entity.pay.PayUnionpayMerEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pay.PayUnionpayMerService;
import com.taro.service.pay.PayUnionpayMerTerService;
import com.taro.service.pay.PayUnionpayService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.SecurityMyUtils;
import com.taro.utils.Utility;

/**
 *<p>Title:PayUnionpayMerServiceImpl.java</p>
 *<p>Description:银联商户号 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:52
 */
@Service
public class PayUnionpayMerServiceImpl extends AbstractService<PayUnionpayMerEntity> implements PayUnionpayMerService {
	
	@Autowired
	private PayUnionpayMerDao payUnionpayMerDao;
	
    @Override
    protected PayUnionpayMerDao getDao() {
        return payUnionpayMerDao;
    }

	@Autowired
	private PayUnionpayService payUnionpayService;
	
	@Autowired
	private PayUnionpayMerTerService payUnionpayMerTerService;

    @Override
    public List<PayUnionpayMerEntity> listParentMer(Map<String, Object> parameter) {
        return getDao().listParentMer(parameter);
    }

    @Override
    public List<PayUnionpayMerEntity> listPayUnionpayMer (Map<String, Object> parameter) {
        List<PayUnionpayMerEntity> reuslt = getDao().listPayUnionpayMer(parameter);
        return reuslt;
    }
    
    @Override
    public Page<PayUnionpayMerEntity> listPayUnionpayMer (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<PayUnionpayMerEntity> reuslt = getDao().listPayUnionpayMer(parameter);
        Page<PayUnionpayMerEntity> page = new Page<>(reuslt);
        return page;
    }
    
    @Override
    public PayUnionpayMerEntity savePayUnionpayMer (PayUnionpayMerEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	if(MyStringUtil.isBlank(model.getIsCur())) {
    		model.setIsCur("0");
    	}
    	if(MyStringUtil.isBlank(model.getName())) {
            throw new BusinessException("商户号名称不能为空!");
    	}
    	if(MyStringUtil.isBlank(model.getTenants_pid()) && "0".equals(model.getIsCur())) {
            throw new BusinessException("商户号所属商户不能为空!");
    	}

    	Map<String, Object> queryMap = new HashMap<>();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("nameEq", model.getName());
    	queryMap.put("unionpay_pid", model.getUnionpay_pid());
    	List<PayUnionpayMerEntity> queryList = super.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("名称[" + model.getName() + "]已存在,请重新编辑!");
    	}

    	queryMap.clear();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("tenants_pid", model.getTenants_pid());
    	queryList = super.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("商户[" + model.getTenants_name() + "]已绑定商户号[" + queryList.get(0).getName() + "],请重新选择!");
    	}

    	queryMap.clear();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("tenants_pid", model.getTenants_pid());
    	List<PayUnionpayEntity> queryUnionpayList = payUnionpayService.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryUnionpayList)) {
            throw new BusinessException("商户[" + model.getTenants_name() + "]已绑定支付组[" + queryUnionpayList.get(0).getName() + "],请重新选择!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		PayUnionpayMerEntity oldModel = super.get(model.getId());
    		if(null == oldModel) {
                throw new BusinessException("查询对象错误!");
    		}
    		// 若支付组修改了租户,则同步修改终端号的租户
    		if(StringUtils.isNotBlank(oldModel.getTenants_pid())
    				&& StringUtils.isNotBlank(model.getTenants_pid())
    				&& !oldModel.getTenants_pid().equals(model.getTenants_pid())) {
    			payUnionpayMerTerService.updateTenants(model.getId(), model.getTenants_pid());
    		}
    		
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
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("ids", pids);
        String currentDate = DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS);
        parameter.put("lastModifiedTime", currentDate);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            String userId = SecurityMyUtils.getUserId();
            if (userId != null) {
                parameter.put("lastModifier", userId);
            }
        }
        return getDao().deleteAllByUnionpayPid(parameter);
    }
}