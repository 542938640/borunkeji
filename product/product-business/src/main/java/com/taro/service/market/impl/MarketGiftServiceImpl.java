package com.taro.service.market.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.market.MarketGiftDao;
import com.taro.entity.market.MarketGiftEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.file.FileManageService;
import com.taro.service.market.MarketGiftService;

/**
 *<p>Title:MarketGiftServiceImpl.java</p>
 *<p>Description:积分换礼 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:20:40
 */
@Service
public class MarketGiftServiceImpl extends AbstractService<MarketGiftEntity> implements MarketGiftService {
	
	@Autowired
	private MarketGiftDao marketGiftDao;
	
    @Override
    protected MarketGiftDao getDao() {
        return marketGiftDao;
    }

	@Autowired
	private FileManageService fileManageService;
	
    @Override
    public MarketGiftEntity saveMarketGift (MarketGiftEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}

    	//激活照片
    	if(StringUtils.isNotBlank(model.getImage())) {
    		fileManageService.activation(model.getImage());
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
    	
        return model;
    }
}