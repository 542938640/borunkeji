package com.taro.entity.advert;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:AdvertHomeEntity.java</p>
 *<p>Description:首页广告 Entity(对应表名:t_advert_home)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 15:52:12
 */
public class AdvertHomeEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 设备id
    private String device_pid;
	// 设备Did
    private String device_did;
	// 素材id
    private String file_pid;
	// 素材类型(0:未上传,1:图片,2:视频)
    private String file_type;
	// 广告内容
    private String content;
	// 停留时间
    private Integer stop_time;
	// 状态(0:未发布,1:已发布)
    private String status;
	// 排序
    private Integer sort;
	// 
    private String c1;
	// 
    private String c2;
	// 
    private String c3;
	// 
    private String c4;
	// 
    private String c5;

    //删除的图片id
    private String deleteFileId;
    
	/**
	 * <b>Summary:设置设备id</b>
	 * setDevice_pid
	 * @param device_pid
	 */
    public void setDevice_pid(String device_pid) {
    	this.device_pid = device_pid;
    }
    
    /**
	 * <b>Summary:获取设备id</b>
	 * getDevice_pid
	 * @return String
	 */
    public String getDevice_pid() {
    	return device_pid;
    }
	/**
	 * <b>Summary:设置设备Did</b>
	 * setDevice_did
	 * @param device_did
	 */
    public void setDevice_did(String device_did) {
    	this.device_did = device_did;
    }
    
    /**
	 * <b>Summary:获取设备Did</b>
	 * getDevice_did
	 * @return String
	 */
    public String getDevice_did() {
    	return device_did;
    }
	/**
	 * <b>Summary:设置素材id</b>
	 * setFile_pid
	 * @param file_pid
	 */
    public void setFile_pid(String file_pid) {
    	this.file_pid = file_pid;
    }
    
    /**
	 * <b>Summary:获取素材id</b>
	 * getFile_pid
	 * @return String
	 */
    public String getFile_pid() {
    	return file_pid;
    }
	/**
	 * <b>Summary:设置素材类型(0:未上传,1:图片,2:视频)</b>
	 * setFile_type
	 * @param file_type
	 */
    public void setFile_type(String file_type) {
    	this.file_type = file_type;
    }
    
    /**
	 * <b>Summary:获取素材类型(0:未上传,1:图片,2:视频)</b>
	 * getFile_type
	 * @return String
	 */
    public String getFile_type() {
    	return file_type;
    }
	/**
	 * <b>Summary:设置广告内容</b>
	 * setContent
	 * @param content
	 */
    public void setContent(String content) {
    	this.content = content;
    }
    
    /**
	 * <b>Summary:获取广告内容</b>
	 * getContent
	 * @return String
	 */
    public String getContent() {
    	return content;
    }
	/**
	 * <b>Summary:设置停留时间</b>
	 * setStop_time
	 * @param stop_time
	 */
    public void setStop_time(Integer stop_time) {
    	this.stop_time = stop_time;
    }
    
    /**
	 * <b>Summary:获取停留时间</b>
	 * getStop_time
	 * @return Integer
	 */
    public Integer getStop_time() {
    	return stop_time;
    }
	/**
	 * <b>Summary:设置状态(0:未发布,1:已发布)</b>
	 * setStatus
	 * @param status
	 */
    public void setStatus(String status) {
    	this.status = status;
    }
    
    /**
	 * <b>Summary:获取状态(0:未发布,1:已发布)</b>
	 * getStatus
	 * @return String
	 */
    public String getStatus() {
    	return status;
    }
	/**
	 * <b>Summary:设置排序</b>
	 * setSort
	 * @param sort
	 */
    public void setSort(Integer sort) {
    	this.sort = sort;
    }
    
    /**
	 * <b>Summary:获取排序</b>
	 * getSort
	 * @return Integer
	 */
    public Integer getSort() {
    	return sort;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC1
	 * @param c1
	 */
    public void setC1(String c1) {
    	this.c1 = c1;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC1
	 * @return String
	 */
    public String getC1() {
    	return c1;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC2
	 * @param c2
	 */
    public void setC2(String c2) {
    	this.c2 = c2;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC2
	 * @return String
	 */
    public String getC2() {
    	return c2;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC3
	 * @param c3
	 */
    public void setC3(String c3) {
    	this.c3 = c3;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC3
	 * @return String
	 */
    public String getC3() {
    	return c3;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC4
	 * @param c4
	 */
    public void setC4(String c4) {
    	this.c4 = c4;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC4
	 * @return String
	 */
    public String getC4() {
    	return c4;
    }
	/**
	 * <b>Summary:设置</b>
	 * setC5
	 * @param c5
	 */
    public void setC5(String c5) {
    	this.c5 = c5;
    }
    
    /**
	 * <b>Summary:获取</b>
	 * getC5
	 * @return String
	 */
    public String getC5() {
    	return c5;
    }

	public String getDeleteFileId() {
		return deleteFileId;
	}

	public void setDeleteFileId(String deleteFileId) {
		this.deleteFileId = deleteFileId;
	}

}