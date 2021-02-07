package com.taro.entity.pub;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PubStructureEntity.java</p>
 *<p>Description:结构定义 Entity(对应表名:t_pub_structure)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public class PubStructureEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String code;									//结构编码
    private String name;									//结构名称

    private String detailDeleteIds;							//结构节点关系信息删除的id
    
    private List<PubStructureDetailEntity> detailList;		//结构节点关系信息List

	public String getDetailDeleteIds() {
		return detailDeleteIds;
	}
	public void setDetailDeleteIds(String detailDeleteIds) {
		this.detailDeleteIds = detailDeleteIds;
	}
	public List<PubStructureDetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<PubStructureDetailEntity> detailList) {
		this.detailList = detailList;
	}
	/**
	*<b>Summary:设置结构编码</b>
	* setCode
	* @param code
	*/
    public void setCode(String code){
    	this.code = code;
    }
    /**
	*<b>Summary:获取结构编码</b>
	* getCode
	* @return
	*/
    public String getCode(){
    	return code;
    }
	/**
	*<b>Summary:设置结构名称</b>
	* setName
	* @param name
	*/
    public void setName(String name){
    	this.name = name;
    }
    /**
	*<b>Summary:获取结构名称</b>
	* getName
	* @return
	*/
    public String getName(){
    	return name;
    }
}