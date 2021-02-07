package com.taro.service.advert.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.taro.dao.advert.AdvertActivitiesDao;
import com.taro.entity.advert.AdvertActivitiesEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.advert.AdvertActivitiesService;
import com.taro.service.device.TSlotInfoService;
import com.taro.service.file.FileManageService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:AdvertActivitiesServiceImpl.java</p>
 *<p>Description:活动广告 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:23:49
 */
@Service
public class AdvertActivitiesServiceImpl extends AbstractService<AdvertActivitiesEntity> implements AdvertActivitiesService {
	
	@Autowired
	private AdvertActivitiesDao advertActivitiesDao;
	
    @Override
    protected AdvertActivitiesDao getDao() {
        return advertActivitiesDao;
    }

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private TSlotInfoService tSlotInfoService;
	
    @Override
    public AdvertActivitiesEntity saveAdvertActivities (AdvertActivitiesEntity model) {
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
    	
    	Map<String, Object> queryMap = Maps.newHashMap();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("device_did", model.getDevice_did());
    	queryMap.put("advert_type", model.getAdvert_type());
    	queryMap.put("advert_class", model.getAdvert_class());
    	List<AdvertActivitiesEntity> queryList = getDao().list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("活动类型已存在,请重新选择!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		model.setStatus("0");
    		super.save(model);
    	}else {
    		if("1".equals(model.getStatus())) {
    	    	// 通知socket活动更新
    	    	tSlotInfoService.updateSiDown(model.getDevice_did());
    		}
    		super.update(model);
    	}
    	
    	
        return model;
    }

    @Override
    public AdvertActivitiesEntity publish(String id) {
        if (MyStringUtil.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        AdvertActivitiesEntity model = getDao().get(id);
        if(null == model) {
            throw new BusinessException("查询对象为空");
        }
        AdvertActivitiesEntity saveBean = new AdvertActivitiesEntity();
        saveBean.setId(id);
        saveBean.setStatus("1");
        super.update(saveBean);

    	// 通知socket活动更新
    	tSlotInfoService.updateSiDown(model.getDevice_did());
        
        return model;
    }

    @Override
    public int deleteAll(String ids) {
    	if (MyStringUtil.isNotBlank(ids)) {
    		String[] idArr = ids.split(",");
    		if (null != idArr && idArr.length > 0) {
    			AdvertActivitiesEntity model = null;
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