package com.taro.service.advert.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.taro.dao.advert.AdvertEndDao;
import com.taro.entity.advert.AdvertEndDetailEntity;
import com.taro.entity.advert.AdvertEndEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.advert.AdvertEndDetailService;
import com.taro.service.advert.AdvertEndService;
import com.taro.service.device.TSlotInfoService;
import com.taro.service.file.FileManageService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.UuidUtil;

/**
 *<p>Title:AdvertEndServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-04 10:14:01
 */
@Service
public class AdvertEndServiceImpl extends AbstractService<AdvertEndEntity> implements AdvertEndService {
	
	@Autowired
	private AdvertEndDao advertEndDao;
	
    @Override
    protected AdvertEndDao getDao() {
        return advertEndDao;
    }

	@Autowired
	private AdvertEndDetailService advertEndDetailService;

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private TSlotInfoService tSlotInfoService;
	
    @Override
    public AdvertEndEntity saveAdvertEnd(AdvertEndEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}

    	//激活照片
    	if(StringUtils.isNotBlank(model.getFile_pid())) {
    		fileManageService.activation(model.getFile_pid());
    	}
    	
    	//删除前端已删除的文件
    	if(StringUtils.isNotBlank(model.getDeleteFileId())) {
    		fileManageService.deleteFile(model.getDeleteFileId());
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
    	if(CollectionUtils.isNotEmpty(model.getDetailList())) {
    		for(AdvertEndDetailEntity detailModel : model.getDetailList()) {
    			detailModel.setEnd_pid(model.getId());
    			detailModel.setDevice_did(model.getDevice_did());
    			if(MyStringUtil.isBlank(detailModel.getId())) {
    				advertEndDetailService.save(detailModel);
    			}else {
    				advertEndDetailService.update(detailModel);
    			}
    		}
    	}
    	
    	// 通知socket活动更新
    	tSlotInfoService.updateSiDown(model.getDevice_did());
    	
        return model;
    }

    @Override
    public AdvertEndEntity getByDeviceDid(String device_did) {
        if (MyStringUtil.isBlank(device_did)) {
            throw new BusinessException("传入设备did不能为空");
        }
        
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("sysFlag", "1");
        queryMap.put("device_did", device_did);
        List<AdvertEndEntity> queryList = super.list(queryMap);
        if(CollectionUtils.isNotEmpty(queryList)) {
        	AdvertEndEntity model = queryList.get(0);
        	model.setJfhl("");
        	model.setXycj("");
        	model.setWxjjk("");
        	model.setSjyh("");
        	model.setWxxyk("");
        	model.setYsf("");
        	model.setZfb("");

        	if(MyStringUtil.isBlank(model.getFile_pid())) {
        		model.setFile_pid(UuidUtil.get32UUID());
        	}
        	
        	if(MyStringUtil.isBlank(model.getFile_type())) {
        		model.setFile_type("0");
        	}
        	
        	queryMap.clear();
            queryMap.put("sysFlag", "1");
            queryMap.put("end_did", model.getId());
            queryMap.put("device_did", device_did);
            model.setDetailList(advertEndDetailService.list(queryMap));
            
            return model;
        }
        
        return new AdvertEndEntity();
    }

    @Override
    public AdvertEndEntity saveUseWait(AdvertEndEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	AdvertEndEntity saveModel = new AdvertEndEntity();
    	saveModel.setDevice_did(model.getDevice_did());
    	saveModel.setUsewait_flag(model.getUsewait_flag());
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		saveModel.setId(model.getId());
    		super.update(model);
    	}

    	// 通知socket活动更新
    	tSlotInfoService.updateSiDown(model.getDevice_did());
    	
        return model;
    }

    @Override
    public AdvertEndEntity getUseWaitByDeviceDid(String device_did) {
        if (MyStringUtil.isBlank(device_did)) {
            throw new BusinessException("传入设备did不能为空");
        }
        
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("sysFlag", "1");
        queryMap.put("device_did", device_did);
        List<AdvertEndEntity> queryList = super.list(queryMap);
        if(CollectionUtils.isNotEmpty(queryList)) {
        	AdvertEndEntity model = new AdvertEndEntity();
        	model.setId(queryList.get(0).getId());
        	model.setDevice_did(queryList.get(0).getDevice_did());
        	model.setUsewait_flag(queryList.get(0).getUsewait_flag());
            return model;
        }
        
        return null;
    }

    @Override
    public int deleteAll(String ids) {
    	if (MyStringUtil.isNotBlank(ids)) {
    		String[] idArr = ids.split(",");
    		if (null != idArr && idArr.length > 0) {
    			AdvertEndEntity model = null;
        		for (String id : idArr) {
        			model = super.get(id);
        			if (null != model && MyStringUtil.isNotBlank(model.getDevice_did())) {
            	    	// 通知socket活动更新
            	    	tSlotInfoService.updateSiDown(model.getDevice_did());
        			}
        		}
    		}
    	}
        return super.deleteAll(ids);
    }
    
}