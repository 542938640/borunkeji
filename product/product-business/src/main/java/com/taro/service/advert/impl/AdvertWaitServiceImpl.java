package com.taro.service.advert.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.advert.AdvertWaitDao;
import com.taro.entity.advert.AdvertWaitEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.advert.AdvertWaitService;
import com.taro.service.device.TSlotInfoService;
import com.taro.service.file.FileManageService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:AdvertWaitServiceImpl.java</p>
 *<p>Description:待机广告 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:22:08
 */
@Service
public class AdvertWaitServiceImpl extends AbstractService<AdvertWaitEntity> implements AdvertWaitService {
	
	@Autowired
	private AdvertWaitDao advertWaitDao;
	
    @Override
    protected AdvertWaitDao getDao() {
        return advertWaitDao;
    }

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private TSlotInfoService tSlotInfoService;
    
    @Override
    public AdvertWaitEntity saveAdvertWait (AdvertWaitEntity model) {
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
    public AdvertWaitEntity publish(String id) {
        if (MyStringUtil.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        AdvertWaitEntity model = getDao().get(id);
        if(null == model) {
            throw new BusinessException("查询对象为空");
        }
        AdvertWaitEntity saveBean = new AdvertWaitEntity();
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
    			AdvertWaitEntity model = null;
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