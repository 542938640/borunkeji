package com.taro.service.market.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taro.common.Page;
import com.taro.dao.market.TProductDao;
import com.taro.entity.market.OwnProductsEntity;
import com.taro.entity.market.TProductEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.file.FileManageService;
import com.taro.service.market.OwnProductsService;
import com.taro.service.market.TProductService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:TProductServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:17:41
 */
@Service
public class TProductServiceImpl extends AbstractService<TProductEntity> implements TProductService {
	
	@Autowired
	private TProductDao tProductDao;
	
    @Override
    protected TProductDao getDao() {
        return tProductDao;
    }

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private OwnProductsService ownProductsService;
	
    @Override
    public Page<TProductEntity> listProduct (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<TProductEntity> reuslt = getDao().listProduct(parameter);
        Page<TProductEntity> page = new Page<TProductEntity>(reuslt);
        return page;
    }
    
    @Override
    public TProductEntity saveTProduct (TProductEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}

    	//激活照片
    	if(StringUtils.isNotBlank(model.getPdimgurl())) {
    		fileManageService.activation(model.getPdimgurl());
    	}
    	
    	//删除前端已删除的文件
    	if(StringUtils.isNotBlank(model.getDeleteFileId())) {
    		fileManageService.deleteFile(model.getDeleteFileId());
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		model.setCreatedate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        	model.setPdstatus("0");
        	model.setPstatus(0);
    		super.save(model);

        	if(MyStringUtil.isNotBlank(model.getTenants_pid())) {
        		OwnProductsEntity ownProductsEntity = new OwnProductsEntity();
        		ownProductsEntity.setProductid(model.getId());
        		ownProductsEntity.setTenantid(model.getTenants_pid());
        		ownProductsEntity.setIsdeleted(0);
        		ownProductsService.save(ownProductsEntity);
        	}
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }

    @Override
    public TProductEntity saveTProductEnable (TProductEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}else if(MyStringUtil.isBlank(model.getId())) {
            throw new BusinessException("商品id不能为空!");
    	}else if(MyStringUtil.isBlank(model.getEnable())) {
            throw new BusinessException("商品启用状态不能为空!");
    	}

    	TProductEntity saveModel = super.get(model.getId());
    	if(null == saveModel) {
            throw new BusinessException("商品id查询为空!");
    	}
    	saveModel.setEnable(model.getEnable());
    	super.update(saveModel);
    	
        return model;
    }
}