package com.taro.entity.notice;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:NoticeEntity.java</p>
 *<p>Description:信息发布 Entity(对应表名:t_notice)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-16 10:16:01
 */
public class NoticeEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 标题
    private String title;
	// 内容
    private String content;
	// 是否发布
    private String is_publish;
	// 发布人
    private String publish_user_pid;
	// 发布时间
    private String publish_time;
    
    private String publish_user_name;
    
    private List<NoticeObjectEntity> objList;

	/**
	 * <b>Summary:设置标题</b>
	 * setTitle
	 * @param title
	 */
    public void setTitle(String title) {
    	this.title = title;
    }
    
    /**
	 * <b>Summary:获取标题</b>
	 * getTitle
	 * @return String
	 */
    public String getTitle() {
    	return title;
    }
	/**
	 * <b>Summary:设置内容</b>
	 * setContent
	 * @param content
	 */
    public void setContent(String content) {
    	this.content = content;
    }
    
    /**
	 * <b>Summary:获取内容</b>
	 * getContent
	 * @return String
	 */
    public String getContent() {
    	return content;
    }
	/**
	 * <b>Summary:设置是否发布</b>
	 * setIs_publish
	 * @param is_publish
	 */
    public void setIs_publish(String is_publish) {
    	this.is_publish = is_publish;
    }
    
    /**
	 * <b>Summary:获取是否发布</b>
	 * getIs_publish
	 * @return String
	 */
    public String getIs_publish() {
    	return is_publish;
    }
	/**
	 * <b>Summary:设置发布人</b>
	 * setPublish_user_pid
	 * @param publish_user_pid
	 */
    public void setPublish_user_pid(String publish_user_pid) {
    	this.publish_user_pid = publish_user_pid;
    }
    
    /**
	 * <b>Summary:获取发布人</b>
	 * getPublish_user_pid
	 * @return String
	 */
    public String getPublish_user_pid() {
    	return publish_user_pid;
    }
	/**
	 * <b>Summary:设置发布时间</b>
	 * setPublish_time
	 * @param publish_time
	 */
    public void setPublish_time(String publish_time) {
    	this.publish_time = publish_time;
    }
    
    /**
	 * <b>Summary:获取发布时间</b>
	 * getPublish_time
	 * @return String
	 */
    public String getPublish_time() {
    	return publish_time;
    }

	public String getPublish_user_name() {
		return publish_user_name;
	}

	public void setPublish_user_name(String publish_user_name) {
		this.publish_user_name = publish_user_name;
	}

	public List<NoticeObjectEntity> getObjList() {
		return objList;
	}

	public void setObjList(List<NoticeObjectEntity> objList) {
		this.objList = objList;
	}

}