package com.taro.entity;

import java.io.Serializable;

/**
 * @Package: com.taro.tdevice.entity
 * @File: IdEntity.java
 * @Description: TODO
 * @Author: tanquanlong
 * @Date: 2016年12月13日
 * @Copyright taro(c)2010-2016
 */
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = -6720526474697160866L;
	// 主键
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
