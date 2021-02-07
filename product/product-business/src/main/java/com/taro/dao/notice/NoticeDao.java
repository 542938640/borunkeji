package com.taro.dao.notice;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.notice.NoticeEntity;

/**
 *<p>Title:NoticeDao.java</p>
 *<p>Description:信息发布 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:16:01
 */
public interface NoticeDao extends AbstractDao<NoticeEntity> {

    List<NoticeEntity> listHome(Map<String, Object> parameter);
    
}