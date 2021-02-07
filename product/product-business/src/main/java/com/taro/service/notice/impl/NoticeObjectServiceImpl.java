package com.taro.service.notice.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.notice.NoticeObjectDao;
import com.taro.entity.notice.NoticeObjectEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.notice.NoticeObjectService;
import com.taro.utils.MyStringUtil;

/**
 *<p>Title:NoticeObjectServiceImpl.java</p>
 *<p>Description:信息发布对象 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:15:50
 */
@Service
public class NoticeObjectServiceImpl extends AbstractService<NoticeObjectEntity> implements NoticeObjectService {
	
	@Autowired
	private NoticeObjectDao noticeObjectDao;
	
    @Override
    protected NoticeObjectDao getDao() {
        return noticeObjectDao;
    }
    
    @Override
    public NoticeObjectEntity saveNoticeObject (NoticeObjectEntity model) {
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
    public int deleteNoticeObject (String notice_pid) {
        if (MyStringUtil.isBlank(notice_pid)) {
            throw new BusinessException("传入对象id不能为空");
        }
        return getDao().deleteNoticeObject(notice_pid);
    }
    
}