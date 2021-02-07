package com.taro.service.device.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.taro.common.Page;
import com.taro.dao.device.DeviceExtDao;
import com.taro.entity.device.DeviceExtEntity;
import com.taro.entity.device.DeviceInfoEntity;
import com.taro.entity.pay.PayUnionpayMerEntity;
import com.taro.entity.pay.TMachineMatchPayInfoEntity;
import com.taro.entity.pay.TPaySettingEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.device.DeviceExtService;
import com.taro.service.device.DeviceInfoService;
import com.taro.service.device.DeviceTenantsService;
import com.taro.service.pay.PayUnionpayMerService;
import com.taro.service.pay.TMachineMatchPayInfoService;
import com.taro.service.pay.TPaySettingService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:DeviceExtServiceImpl.java</p>
 *<p>Description:设备信息扩展 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-19 09:52:36
 */
@Service
public class DeviceExtServiceImpl extends AbstractService<DeviceExtEntity> implements DeviceExtService {
	
	@Autowired
	private DeviceExtDao deviceExtDao;
	
    @Override
    protected DeviceExtDao getDao() {
        return deviceExtDao;
    }

	@Autowired
	private DeviceInfoService deviceInfoService;

	@Autowired
	private TPaySettingService tPaySettingService;

	@Autowired
	private TMachineMatchPayInfoService tMachineMatchPayInfoService;

	@Autowired
	private PayUnionpayMerService payUnionpayMerService;

	@Autowired
	private DeviceTenantsService deviceTenantsService;
	
    @Override
    public List<DeviceExtEntity> listSelectDevice(Map<String, Object> parameter) {
        List<DeviceExtEntity> reuslt = getDao().listSelectDevice(parameter);
        return reuslt;
    }
    
    @Override
    public DeviceExtEntity getDeviceExt(String id) {
        if (MyStringUtil.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        
        DeviceExtEntity model = getDao().getDeviceExt(id);
        if(null == model) {
            throw new BusinessException("查询对象为空");
        }
        
        String tenants_pid = model.getTenants_pid();
        if (MyStringUtil.isBlank(tenants_pid)) {
            throw new BusinessException("请先分配所属商户");
        }
        
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("tenants_pid", tenants_pid);
        List<PayUnionpayMerEntity> unionMerList = payUnionpayMerService.listParentMer(queryMap);
        if(CollectionUtils.isEmpty(unionMerList)) {
            throw new BusinessException("查询支付组及商户号错误");
        }
        
        Map<String, PayUnionpayMerEntity> unionpayMerMap = new HashMap<>();
        for(PayUnionpayMerEntity unionMer : unionMerList) {
        	unionpayMerMap.put(unionMer.getTenants_pid(), unionMer);
        }
        
        PayUnionpayMerEntity unionMer = null;
        String parent_pid = "";
        String group_pid = "";
        String unionpay_pid = "";
        String unionpay_name = "";
        String unionpay_mer_pid = "";
        String unionpay_mer_name = "";
        // 找对应的商户号
        do {
        	unionMer = unionpayMerMap.get(tenants_pid);
        	if(MyStringUtil.isNotBlank(unionMer.getUnionpay_mer_pid())) {
        		unionpay_pid = unionMer.getUnionpay_pid();
        		unionpay_name = unionMer.getUnionpay_name();
        		unionpay_mer_pid = unionMer.getUnionpay_mer_pid();
        		unionpay_mer_name = unionMer.getUnionpay_mer_name();
        		break;
        	}
        	if(MyStringUtil.isNotBlank(unionMer.getGroup_pid()) && MyStringUtil.isBlank(group_pid)) {
        		group_pid = unionMer.getGroup_pid();
        	}
        	parent_pid = unionMer.getParent_tenants_pid();
        }while(unionpayMerMap.containsKey(parent_pid));
        
        // 如果未找到,就找对应的支付组,再取通用商户号
        if(MyStringUtil.isBlank(unionpay_pid)) {
            if(MyStringUtil.isBlank(group_pid)) {
                throw new BusinessException("根据商户未找到对应的支付组及商户号,请确认");
            }
            // 找支付组对应的通用商户号
            queryMap.clear();
            queryMap.put("sysFlag", "1");
            queryMap.put("isCur", "1");
            queryMap.put("unionpay_pid", group_pid);
            List<PayUnionpayMerEntity> queryCurrencyList = payUnionpayMerService.listPayUnionpayMer(queryMap);
            if(CollectionUtils.isEmpty(queryCurrencyList)) {
                throw new BusinessException("根据商户未找到对应的支付组的通用商户号,请确认");
            }
    		unionpay_pid = queryCurrencyList.get(0).getUnionpay_pid();
    		unionpay_name = queryCurrencyList.get(0).getUnionpay_name();
    		unionpay_mer_pid = queryCurrencyList.get(0).getUnionpay_mer_pid();
    		unionpay_mer_name = queryCurrencyList.get(0).getUnionpay_mer_name();
        }
        
        model.setUnionpay_pid(unionpay_pid);
        model.setUnionpay_name(unionpay_name);
        model.setUnionpay_mer_pid(unionpay_mer_pid);
        model.setUnionpay_mer_name(unionpay_mer_name);
        
        return model;
    }
    
    @Override
    public Page<DeviceExtEntity> listDevice (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<DeviceExtEntity> reuslt = getDao().listDevice(parameter);
        Page<DeviceExtEntity> page = new Page<>(reuslt);
        return page;
    }
    
    @Override
    public DeviceExtEntity saveDeviceExt (DeviceExtEntity model) {
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
    public DeviceExtEntity saveDeviceTenants(DeviceExtEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
    	
    	// 所属商户
    	if(MyStringUtil.isNotBlank(model.getTenants_pid())
    			&& CollectionUtils.isNotEmpty(model.getIdList())) {
    		String tenants_pid = model.getTenants_pid();
    		DeviceExtEntity deviceExtEntity = null;
    		DeviceInfoEntity deviceInfoEntity = null;
    		Map<String, Object> queryMap = Maps.newHashMap();
    		List<DeviceInfoEntity> queryInfoList = null;
    		List<DeviceExtEntity> queryExtList = null;
    		for(String did : model.getIdList()) {
    			if(MyStringUtil.isBlank(did)) {
    				continue;
    			}
    			
    			queryMap.clear();
    			queryMap.put("did", did);
    			queryInfoList = deviceInfoService.list(queryMap);
    			if(CollectionUtils.isEmpty(queryInfoList)) {
    				continue;
    			}
    			String pid = queryInfoList.get(0).getId();// 查出多个也只取第一个
    			deviceInfoEntity = new DeviceInfoEntity();
    			deviceInfoEntity.setId(pid);
    			deviceInfoEntity.setDibelonguser(tenants_pid);
        		deviceInfoService.update(deviceInfoEntity);

    			deviceExtEntity = new DeviceExtEntity();
    			deviceExtEntity.setTenants_pid(tenants_pid);
    			
        		queryMap.put("sysFlag", "1");
    			queryMap.put("device_did", did);
    			queryExtList = super.list(queryMap);
    			if(CollectionUtils.isNotEmpty(queryExtList)) {
    				deviceExtEntity.setId(queryExtList.get(0).getId());
    				deviceExtEntity.setStatus("1");
        			super.update(deviceExtEntity);
    			}else {
    				deviceExtEntity.setDevice_pid(pid);
    				deviceExtEntity.setDevice_did(did);
    				deviceExtEntity.setStatus("1");
    				super.save(deviceExtEntity);
    			}
    			
    		}
    		
    	}
    	
        return model;
    }

    @Override
    public DeviceExtEntity saveDeviceAccount(DeviceExtEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        if(MyStringUtil.isBlank(model.getId())) {
            throw new BusinessException("对象ID不能为空");
        }
        if(MyStringUtil.isBlank(model.getAccount_pid())) {
            throw new BusinessException("绑定账号不能为空");
        }
        
        DeviceExtEntity oldModel = super.get(model.getId());
        if(null == oldModel || MyStringUtil.isBlank(oldModel.getTenants_pid())) {
            throw new BusinessException("请先分配设备所属商户");
        }
        
        DeviceExtEntity saveModel = new DeviceExtEntity();
        saveModel.setId(model.getId());
        saveModel.setAccount_pid(model.getAccount_pid());
        super.update(saveModel);

    	TPaySettingEntity tPaySettingEntity = null;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("pscreatecompany", model.getAccount_pid());
        List<TPaySettingEntity> queryList = tPaySettingService.list(queryMap);
        if(CollectionUtils.isEmpty(queryList)) {
        	tPaySettingEntity = new TPaySettingEntity();
        	tPaySettingEntity.setPscreatecompany(model.getAccount_pid());
        	tPaySettingService.save(tPaySettingEntity);
        	
        	queryList = tPaySettingService.list(queryMap);
        }
    	tPaySettingEntity = queryList.get(0);

        TMachineMatchPayInfoEntity tMachineMatchPayInfoEntity = null;
        queryMap.clear();
        queryMap.put("mmpimid", oldModel.getDevice_did());
        List<TMachineMatchPayInfoEntity> queryInfoList = tMachineMatchPayInfoService.list(queryMap);
        if(CollectionUtils.isEmpty(queryInfoList)) {
        	tMachineMatchPayInfoEntity = new TMachineMatchPayInfoEntity();
            tMachineMatchPayInfoEntity.setMmpimid(oldModel.getDevice_did());
            tMachineMatchPayInfoEntity.setMmpisstid(Integer.valueOf(tPaySettingEntity.getId()));
            tMachineMatchPayInfoService.save(tMachineMatchPayInfoEntity);
        }else {
        	tMachineMatchPayInfoEntity = queryInfoList.get(0);
            tMachineMatchPayInfoEntity.setMmpisstid(Integer.valueOf(tPaySettingEntity.getId()));
            tMachineMatchPayInfoService.update(tMachineMatchPayInfoEntity);
        }
        
        return model;
    }

    @Override
    public DeviceExtEntity saveDeviceUnbundling(DeviceExtEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        
        if(CollectionUtils.isNotEmpty(model.getIdList())) {
    		DeviceExtEntity deviceExtEntity = null;
    		DeviceInfoEntity deviceInfoEntity = null;
    		Map<String, Object> queryMap = Maps.newHashMap();
    		List<DeviceInfoEntity> queryInfoList = null;
    		List<DeviceExtEntity> queryExtList = null;
    		List<TMachineMatchPayInfoEntity> queryPayInfoList = null;
    		for(String did : model.getIdList()) {
    			if(MyStringUtil.isBlank(did)) {
    				continue;
    			}
    			
    			queryMap.clear();
    			queryMap.put("did", did);
    			queryInfoList = deviceInfoService.list(queryMap);
    			if(CollectionUtils.isNotEmpty(queryInfoList)) {
        			deviceInfoEntity = new DeviceInfoEntity();
        			deviceInfoEntity.setId(queryInfoList.get(0).getId());
        			deviceInfoEntity.setDibelonguser("");
            		deviceInfoService.update(deviceInfoEntity);
    			}

    			queryMap.clear();
        		queryMap.put("sysFlag", "1");
    			queryMap.put("device_did", did);
    			queryExtList = super.list(queryMap);
    			if(CollectionUtils.isNotEmpty(queryExtList)) {
    				DeviceExtEntity queryExtModel = queryExtList.get(0);
    				
        			deviceExtEntity = new DeviceExtEntity();
        			deviceExtEntity.setTenants_pid("");
        			deviceExtEntity.setAccount_pid("");
        			deviceExtEntity.setStatus("0");
    				deviceExtEntity.setId(queryExtModel.getId());
        			super.update(deviceExtEntity);

        			if(MyStringUtil.isNotBlank(queryExtModel.getTenants_pid())) {
        				deviceTenantsService.saveByTenants(did, queryExtModel.getTenants_pid());
        			}
    			}

    			queryMap.clear();
    	        queryMap.put("mmpimid", did);
    	        queryPayInfoList = tMachineMatchPayInfoService.list(queryMap);
    	        if(CollectionUtils.isNotEmpty(queryPayInfoList)) {
    	        	for(TMachineMatchPayInfoEntity payInfo : queryPayInfoList) {
        	            tMachineMatchPayInfoService.delete(payInfo.getId());
    	        	}
    	        }
    			
    		}
        }
    	
    	return model;
    }

    @Override
    public DeviceExtEntity saveDeviceRecycle(DeviceExtEntity model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }else if(MyStringUtil.isBlank(model.getDevice_did())) {
            throw new BusinessException("设备Did不能为空");
        }
        
        String did = model.getDevice_did();

		Map<String, Object> queryMap = Maps.newHashMap();
		queryMap.put("did", did);
		List<DeviceInfoEntity> queryInfoList = deviceInfoService.list(queryMap);
		if(CollectionUtils.isNotEmpty(queryInfoList)) {
			DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
			deviceInfoEntity.setId(queryInfoList.get(0).getId());
			deviceInfoEntity.setDibelonguser("");
    		deviceInfoService.update(deviceInfoEntity);
		}

		queryMap.clear();
		queryMap.put("sysFlag", "1");
		queryMap.put("device_did", did);
		List<DeviceExtEntity> queryExtList = super.list(queryMap);
		if(CollectionUtils.isNotEmpty(queryExtList)) {
			DeviceExtEntity queryExtModel = queryExtList.get(0);
			
			DeviceExtEntity deviceExtEntity = new DeviceExtEntity();
			deviceExtEntity.setTenants_pid("");
			deviceExtEntity.setAccount_pid("");
			deviceExtEntity.setStatus("2");
			deviceExtEntity.setId(queryExtModel.getId());
			super.update(deviceExtEntity);
			
			if(MyStringUtil.isNotBlank(queryExtModel.getTenants_pid())) {
				deviceTenantsService.saveByTenants(did, queryExtModel.getTenants_pid());
			}
		}

		queryMap.clear();
        queryMap.put("mmpimid", did);
        List<TMachineMatchPayInfoEntity> queryPayInfoList = tMachineMatchPayInfoService.list(queryMap);
        if(CollectionUtils.isNotEmpty(queryPayInfoList)) {
        	for(TMachineMatchPayInfoEntity payInfo : queryPayInfoList) {
	            tMachineMatchPayInfoService.delete(payInfo.getId());
        	}
        }
    	
    	return model;
    }
    
}