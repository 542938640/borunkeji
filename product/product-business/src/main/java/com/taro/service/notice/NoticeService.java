package com.taro.service.notice;

import java.util.Map;

import com.taro.common.Page;
import com.taro.entity.notice.NoticeEntity;
import com.taro.service.IService;

/**
 *<p>Title:NoticeService.java</p>
 *<p>Description:信息发布 Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:16:01
 */
public interface NoticeService extends IService<NoticeEntity> {

    Page<NoticeEntity> listHome(int pageNum, int pageSize, Map<String, Object> parameter);
    
	NoticeEntity saveNotice(NoticeEntity model);

	NoticeEntity publish(String id);

	NoticeEntity getNotice(String id);

    int deleteNotice(String ids);
    
}