package com.taro.entity.pub;

import java.io.Serializable;
import java.util.List;

import com.taro.entity.AbstractEntity;

/**
 *<p>Title:PubBaseAttributeEntity.java</p>
 *<p>Description:基础数据类型属性 Entity(对应表名:t_pub_base_attribute)</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public class PubBaseAttributeEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String base_pid;							//基类ID
    private String name;								//名称
    private String refbase_pid;							//引用基类ID
    private String storage_column;						//存储字段
    private String input_control;						//输入控件
    private Integer order;								//排序
    private String required_flag;						//是否必填
    private String condition_flag;						//是否查询条件

    private String deleteIds;							//删除ids
    
    private List<PubBaseAttributeEntity> list;			//保存list
    
	public String getDeleteIds() {
		return deleteIds;
	}
	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}
	public List<PubBaseAttributeEntity> getList() {
		return list;
	}
	public void setList(List<PubBaseAttributeEntity> list) {
		this.list = list;
	}
	/**
	*<b>Summary:设置基类ID</b>
	* setBase_pid
	* @param base_pid
	*/
    public void setBase_pid(String base_pid){
    	this.base_pid = base_pid;
    }
    /**
	*<b>Summary:获取基类ID</b>
	* getBase_pid
	* @return
	*/
    public String getBase_pid(){
    	return base_pid;
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
	*<b>Summary:设置引用基类ID</b>
	* setRefbase_pid
	* @param refbase_pid
	*/
    public void setRefbase_pid(String refbase_pid){
    	this.refbase_pid = refbase_pid;
    }
    /**
	*<b>Summary:获取引用基类ID</b>
	* getRefbase_pid
	* @return
	*/
    public String getRefbase_pid(){
    	return refbase_pid;
    }
	/**
	*<b>Summary:设置存储字段</b>
	* setStorage_column
	* @param storage_column
	*/
    public void setStorage_column(String storage_column){
    	this.storage_column = storage_column;
    }
    /**
	*<b>Summary:获取存储字段</b>
	* getStorage_column
	* @return
	*/
    public String getStorage_column(){
    	return storage_column;
    }
	/**
	*<b>Summary:设置输入控件</b>
	* setInput_control
	* @param input_control
	*/
    public void setInput_control(String input_control){
    	this.input_control = input_control;
    }
    /**
	*<b>Summary:获取输入控件</b>
	* getInput_control
	* @return
	*/
    public String getInput_control(){
    	return input_control;
    }
	/**
	*<b>Summary:设置排序</b>
	* setOrder
	* @param order
	*/
    public void setOrder(Integer order){
    	this.order = order;
    }
    /**
	*<b>Summary:获取排序</b>
	* getOrder
	* @return
	*/
    public Integer getOrder(){
    	return order;
    }
	/**
	*<b>Summary:设置是否必填</b>
	* setRequired_flag
	* @param required_flag
	*/
    public void setRequired_flag(String required_flag){
    	this.required_flag = required_flag;
    }
    /**
	*<b>Summary:获取是否必填</b>
	* getRequired_flag
	* @return
	*/
    public String getRequired_flag(){
    	return required_flag;
    }
	/**
	*<b>Summary:设置是否查询条件</b>
	* setCondition_flag
	* @param condition_flag
	*/
    public void setCondition_flag(String condition_flag){
    	this.condition_flag = condition_flag;
    }
    /**
	*<b>Summary:获取是否查询条件</b>
	* getCondition_flag
	* @return
	*/
    public String getCondition_flag(){
    	return condition_flag;
    }
}