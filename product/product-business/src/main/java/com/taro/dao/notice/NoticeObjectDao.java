package com.taro.dao.notice;

import com.taro.dao.AbstractDao;
import com.taro.entity.notice.NoticeObjectEntity;

/**
 *<p>Title:NoticeObjectDao.java</p>
 *<p>Description:信息发布对象 Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:15:50
 */
public interface NoticeObjectDao extends AbstractDao<NoticeObjectEntity> {

	int deleteNoticeObject(String notice_pid);
	
}