package com.taro.entity.pub;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PubStructureDetailEntity.java</p>
 *<p>Description:结构节点关系信息 Entity(对应表名:t_pub_structure_detail)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-01 14:21
 */
public class PubStructureDetailEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String structure_pid;							//基类id
    private String parentbase_pid;							//父节点基类ID
    private String childbase_pid;							//子节点基类ID
    
    private String parentbase_name;							//父节点基类名称
    private String childbase_name;							//子节点基类名称

    private List<PubStructureDetailEntity> children;		//子节点list
    
	public String getParentbase_name() {
		return parentbase_name;
	}
	public void setParentbase_name(String parentbase_name) {
		this.parentbase_name = parentbase_name;
	}
	public String getChildbase_name() {
		return childbase_name;
	}
	public void setChildbase_name(String childbase_name) {
		this.childbase_name = childbase_name;
	}
	public List<PubStructureDetailEntity> getChildren() {
		return children;
	}
	public void setChildren(List<PubStructureDetailEntity> children) {
		this.children = children;
	}
	/**
	*<b>Summary:设置</b>
	* setStructure_pid
	* @param structure_pid
	*/
    public void setStructure_pid(String structure_pid){
    	this.structure_pid = structure_pid;
    }
    /**
	*<b>Summary:获取</b>
	* getStructure_pid
	* @return
	*/
    public String getStructure_pid(){
    	return structure_pid;
    }
	/**
	*<b>Summary:设置父节点基类ID</b>
	* setParentbase_pid
	* @param parentbase_pid
	*/
    public void setParentbase_pid(String parentbase_pid){
    	this.parentbase_pid = parentbase_pid;
    }
    /**
	*<b>Summary:获取父节点基类ID</b>
	* getParentbase_pid
	* @return
	*/
    public String getParentbase_pid(){
    	return parentbase_pid;
    }
	/**
	*<b>Summary:设置子节点基类ID</b>
	* setChildbase_pid
	* @param childbase_pid
	*/
    public void setChildbase_pid(String childbase_pid){
    	this.childbase_pid = childbase_pid;
    }
    /**
	*<b>Summary:获取子节点基类ID</b>
	* getChildbase_pid
	* @return
	*/
    public String getChildbase_pid(){
    	return childbase_pid;
    }
}