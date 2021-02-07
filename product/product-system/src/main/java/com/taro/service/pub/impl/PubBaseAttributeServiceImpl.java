package com.taro.service.pub.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.constants.Constant;
import com.taro.dao.pub.PubBaseAttributeDao;
import com.taro.entity.pub.PubBaseAttributeEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pub.PubBaseAttributeService;
import com.taro.utils.Utility;

/**
 *<p>Title:PubBaseAttributeServiceImpl.java</p>
 *<p>Description:基础数据类型属性ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
@Service
public class PubBaseAttributeServiceImpl extends AbstractService<PubBaseAttributeEntity> implements PubBaseAttributeService{
	@Autowired
	private PubBaseAttributeDao pubBaseAttributeDao;
    @Override
    protected PubBaseAttributeDao getDao () {
        return pubBaseAttributeDao;
    }
    
    public PubBaseAttributeEntity saveBaseAttribute(PubBaseAttributeEntity model) {
        if (null == model) {
            throw new BusinessException("保存对象不能为空");
        }
        
        if(StringUtils.isBlank(model.getBase_pid())) {
            throw new BusinessException("基类ID不能为空");
        }
        
        if(StringUtils.isNotBlank(model.getDeleteIds())) {
        	super.deleteAll(model.getDeleteIds());
        }
        
        if(CollectionUtils.isNotEmpty(model.getList())) {
        	for(PubBaseAttributeEntity saveBean : model.getList()) {
        		saveBean.setBase_pid(model.getBase_pid());
        		if(StringUtils.isBlank(saveBean.getId())) {
        			super.save(saveBean);
        		}else {
        			super.update(saveBean);
        		}
        	}
        }
        
        return model;
    }

    public int deleteAllByBasePid(String ids) {
        //校验参数
        Utility.raiseIfWrong(ids, "ids不能为空");

        String[] pids = ids.split(Constant.COMMA);
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("ids", pids);
        String currentDate = DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS);
        parameter.put("lastModifiedTime", currentDate);
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            String userId = SecurityMyUtils.getUserId();
//            if (userId != null) {
//                parameter.put("lastModifier", userId);
//            }
//        }
        return getDao().deleteAllByBasePid(parameter);
    }
}