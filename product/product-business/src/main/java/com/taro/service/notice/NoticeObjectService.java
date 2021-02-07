package com.taro.service.notice;

import com.taro.entity.notice.NoticeObjectEntity;
import com.taro.service.IService;

/**
 *<p>Title:NoticeObjectService.java</p>
 *<p>Description:信息发布对象 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:15:50
 */
public interface NoticeObjectService extends IService<NoticeObjectEntity> {
	
	NoticeObjectEntity saveNoticeObject(NoticeObjectEntity model);

    int deleteNoticeObject(String notice_pid);
    
}