package com.taro.entity.pub;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PubStructureRelationEntity.java</p>
 *<p>Description:结构关系 Entity(对应表名:t_pub_structure_relation)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:56
 */
public class PubStructureRelationEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String structure_pid;							//结构定义ID
    private String structure_detail_pid;					//结构定义明细ID
    private String parentdic_pid;							//父节点基础数据ID
    private String childdic_pid;							//子节点基础数据ID
    
    private String parentdic_name;							//父节点基础数据名称
    private String childdic_name;							//子节点基础数据名称
    private String parentbase_pid;							//父节点基础数据类型ID
    private String childbase_pid;							//子节点基础数据类型ID
    private String parentbase_name;							//父节点基础数据类型名称
    private String childbase_name;							//子节点基础数据类型名称

    private String value1;									//子节点基础数据value1
    private String value2;									//子节点基础数据value2
    private String value3;									//子节点基础数据value3
    private String value4;									//子节点基础数据value4
    private String value5;									//子节点基础数据value5
    
    private List<PubStructureRelationEntity> children;		//子节点list
    private List<PubStructureRelationEntity> relationList;	//保存list
    
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getValue5() {
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
	}
	public List<PubStructureRelationEntity> getRelationList() {
		return relationList;
	}
	public void setRelationList(List<PubStructureRelationEntity> relationList) {
		this.relationList = relationList;
	}
	public String getParentbase_pid() {
		return parentbase_pid;
	}
	public void setParentbase_pid(String parentbase_pid) {
		this.parentbase_pid = parentbase_pid;
	}
	public String getChildbase_pid() {
		return childbase_pid;
	}
	public void setChildbase_pid(String childbase_pid) {
		this.childbase_pid = childbase_pid;
	}
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
	public String getParentdic_name() {
		return parentdic_name;
	}
	public void setParentdic_name(String parentdic_name) {
		this.parentdic_name = parentdic_name;
	}
	public String getChilddic_name() {
		return childdic_name;
	}
	public void setChilddic_name(String childdic_name) {
		this.childdic_name = childdic_name;
	}
	public List<PubStructureRelationEntity> getChildren() {
		return children;
	}
	public void setChildren(List<PubStructureRelationEntity> children) {
		this.children = children;
	}
	/**
	*<b>Summary:设置结构定义ID</b>
	* setStructure_pid
	* @param structure_pid
	*/
    public void setStructure_pid(String structure_pid){
    	this.structure_pid = structure_pid;
    }
    /**
	*<b>Summary:获取结构定义ID</b>
	* getStructure_pid
	* @return
	*/
    public String getStructure_pid(){
    	return structure_pid;
    }
	/**
	*<b>Summary:设置结构定义明细ID</b>
	* setStructure_detail_pid
	* @param structure_detail_pid
	*/
    public void setStructure_detail_pid(String structure_detail_pid){
    	this.structure_detail_pid = structure_detail_pid;
    }
    /**
	*<b>Summary:获取结构定义明细ID</b>
	* getStructure_detail_pid
	* @return
	*/
    public String getStructure_detail_pid(){
    	return structure_detail_pid;
    }
	/**
	*<b>Summary:设置父节点基础数据ID</b>
	* setParentdic_pid
	* @param parentdic_pid
	*/
    public void setParentdic_pid(String parentdic_pid){
    	this.parentdic_pid = parentdic_pid;
    }
    /**
	*<b>Summary:获取父节点基础数据ID</b>
	* getParentdic_pid
	* @return
	*/
    public String getParentdic_pid(){
    	return parentdic_pid;
    }
	/**
	*<b>Summary:设置子节点基础数据ID</b>
	* setChilddic_pid
	* @param childdic_pid
	*/
    public void setChilddic_pid(String childdic_pid){
    	this.childdic_pid = childdic_pid;
    }
    /**
	*<b>Summary:获取子节点基础数据ID</b>
	* getChilddic_pid
	* @return
	*/
    public String getChilddic_pid(){
    	return childdic_pid;
    }
}