package com.taro.entity.activiti;import java.io.Serializable;import com.taro.entity.AbstractEntity;public class UserEntity extends AbstractEntity implements Serializable {	private static final long serialVersionUID = 1L;    private String name;						//用户名称	/**	*<b>Summary:设置用户名称</b>	* setName	* @param name	*/    public void setName(String name){    	this.name = name;    }    /**	*<b>Summary:获取用户名称</b>	* getName	* @return	*/    public String getName(){    	return name;    }}