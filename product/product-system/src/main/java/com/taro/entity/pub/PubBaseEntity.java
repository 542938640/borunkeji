package com.taro.entity.pub;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PubBaseEntity.java</p>
 *<p>Description:基础数据类型 Entity(对应表名:t_pub_base)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public class PubBaseEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String code;						//编码
    private String name;						//名称
    private String parent_pid;					//超类ID
    private Integer order;						//排序
    private String buildflag;					//是否为内置属性（1：是；0：否）
    
    private String parent_name;					//超类名称
    
    private List<PubBaseEntity> children;		//子节点list

    private List<PubBaseAttributeEntity> attributeList;	//属性子节点list
    
	public List<PubBaseAttributeEntity> getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(List<PubBaseAttributeEntity> attributeList) {
		this.attributeList = attributeList;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public List<PubBaseEntity> getChildren() {
		return children;
	}
	public void setChildren(List<PubBaseEntity> children) {
		this.children = children;
	}
	/**
	*<b>Summary:设置编码</b>
	* setCode
	* @param code
	*/
    public void setCode(String code){
    	this.code = code;
    }
    /**
	*<b>Summary:获取编码</b>
	* getCode
	* @return
	*/
    public String getCode(){
    	return code;
    }
	/**
	*<b>Summary:设置名称</b>
	* setName
	* @param name
	*/
    public void setName(String name){
    	this.name = name;
    }
    /**
	*<b>Summary:获取名称</b>
	* getName
	* @return
	*/
    public String getName(){
    	return name;
    }
	/**
	*<b>Summary:设置超类ID</b>
	* setParent_pid
	* @param parent_pid
	*/
    public void setParent_pid(String parent_pid){
    	this.parent_pid = parent_pid;
    }
    /**
	*<b>Summary:获取超类ID</b>
	* getParent_pid
	* @return
	*/
    public String getParent_pid(){
    	return parent_pid;
    }
	/**
	*<b>Summary:设置是否为内置属性（1：是；0：否）</b>
	* setbuildflag
	* @param buildflag
	*/
    public void setBuildflag(String buildflag){
    	this.buildflag = buildflag;
    }
    /**
	*<b>Summary:获取是否为内置属性（1：是；0：否）</b>
	* getbuildflag
	* @return
	*/
    public String getBuildflag(){
    	return buildflag;
    }
}