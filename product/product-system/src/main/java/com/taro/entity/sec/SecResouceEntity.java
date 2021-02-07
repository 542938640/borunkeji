package com.taro.entity.sec;

import java.io.Serializable;
import com.taro.entity.AbstractEntity;

/**
 *<p>Title:SecResouceEntity.java</p>
 *<p>Description:资源表 Entity(对应表名:t_sec_resouce)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:08
 */
public class SecResouceEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String target_id;		//
    private String type_id;		//

	/**
	*<b>Summary:设置</b>
	* setTarget_id
	* @param target_id
	*/
    public void setTarget_id(String target_id){
    	this.target_id = target_id;
    }
    /**
	*<b>Summary:获取</b>
	* getTarget_id
	* @return
	*/
    public String getTarget_id(){
    	return target_id;
    }
	/**
	*<b>Summary:设置</b>
	* setType_id
	* @param type_id
	*/
    public void setType_id(String type_id){
    	this.type_id = type_id;
    }
    /**
	*<b>Summary:获取</b>
	* getType_id
	* @return
	*/
    public String getType_id(){
    	return type_id;
    }
}