package com.taro.service.notice.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.dao.notice.NoticeDao;
import com.taro.entity.notice.NoticeEntity;
import com.taro.entity.notice.NoticeObjectEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.notice.NoticeObjectService;
import com.taro.service.notice.NoticeService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.SecurityMyUtils;

/**
 *<p>Title:NoticeServiceImpl.java</p>
 *<p>Description:信息发布 ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:16:01
 */
@Service
public class NoticeServiceImpl extends AbstractService<NoticeEntity> implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	
    @Override
    protected NoticeDao getDao() {
        return noticeDao;
    }

	@Autowired
	private NoticeObjectService noticeObjectService;

    @Override
    public Page<NoticeEntity> listHome(int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<NoticeEntity> reuslt = getDao().listHome(parameter);
        Page<NoticeEntity> page = new Page<NoticeEntity>(reuslt);
        return page;
    }
    
    @Override
    public NoticeEntity saveNotice (NoticeEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}

        // 内容字段特殊处理，由于数据类型blob特殊性，如果是null值，会在查询时报错。
        if(MyStringUtil.isBlank(model.getContent())){
            model.setContent("");
        }
        
        // 直接发布
        if(MyStringUtil.isNotBlank(model.getIs_publish())
        		&& Constant.YES.equals(model.getIs_publish())) {
        	
        	if(MyStringUtil.isBlank(model.getTitle())) {
                throw new BusinessException("消息标题不能为空!");
        	}

        	if(MyStringUtil.isBlank(model.getContent())) {
                throw new BusinessException("消息内容不能为空!");
        	}

        	if(CollectionUtils.isEmpty(model.getObjList())) {
                throw new BusinessException("下发商户不能为空!");
        	}

        	model.setPublish_user_pid(SecurityMyUtils.getUserId());
        	model.setPublish_time(DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS));
        }
        
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
    	// 先删除所有的下发商户
    	noticeObjectService.deleteNoticeObject(model.getId());
    	
    	// 保存下发商户
    	if(CollectionUtils.isNotEmpty(model.getObjList())) {
    		for(NoticeObjectEntity noticeObject : model.getObjList()) {
    			noticeObject.setNotice_pid(model.getId());
    			noticeObjectService.save(noticeObject);
    		}
    	}
    	
        return model;
    }

    @Override
    public NoticeEntity publish(String id) {
        if (MyStringUtil.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        NoticeEntity model = getDao().get(id);
        if(null == model) {
            throw new BusinessException("查询对象为空");
        }
        NoticeEntity saveBean = new NoticeEntity();
        saveBean.setId(id);
        saveBean.setIs_publish(Constant.YES);
        saveBean.setPublish_user_pid(SecurityMyUtils.getUserId());
        saveBean.setPublish_time(DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS));
        super.update(saveBean);
        
        return model;
    }
    
    @Override
    public NoticeEntity getNotice (String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        NoticeEntity model = getDao().get(id);
        if(null != model) {
        	Map<String, Object> queryMap = new HashMap<>();
        	queryMap.put("notice_pid", id);
        	List<NoticeObjectEntity> objList = noticeObjectService.list(queryMap);
        	if(CollectionUtils.isNotEmpty(objList)) {
        		model.setObjList(objList);
        	}
        }
        return model;
    }

    @Override
    public int deleteNotice(String ids) {
    	int num = super.deleteAll(ids);
    	if(StringUtils.isNotBlank(ids)) {
        	String[] idArr = ids.split(",");
        	if(null != idArr) {
        		for(String id : idArr) {
        			noticeObjectService.deleteNoticeObject(id);
        		}
        	}
    	}
        return num;
    }
}