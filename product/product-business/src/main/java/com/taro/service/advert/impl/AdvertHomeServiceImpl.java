package com.taro.service.advert.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.advert.AdvertHomeDao;
import com.taro.entity.advert.AdvertHomeEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.advert.AdvertHomeService;
import com.taro.service.device.TSlotInfoService;
import com.taro.service.file.FileManageService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:AdvertHomeServiceImpl.java</p>
 *<p>Description:首页广告 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:21:52
 */
@Service
public class AdvertHomeServiceImpl extends AbstractService<AdvertHomeEntity> implements AdvertHomeService {
	
	@Autowired
	private AdvertHomeDao advertHomeDao;
	
    @Override
    protected AdvertHomeDao getDao() {
        return advertHomeDao;
    }

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private TSlotInfoService tSlotInfoService;
    
    @Override
    public AdvertHomeEntity saveAdvertHome (AdvertHomeEntity model) {
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
    public AdvertHomeEntity publish(String id) {
        if (MyStringUtil.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        AdvertHomeEntity model = getDao().get(id);
        if(null == model) {
            throw new BusinessException("查询对象为空");
        }
        AdvertHomeEntity saveBean = new AdvertHomeEntity();
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
    			AdvertHomeEntity model = null;
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