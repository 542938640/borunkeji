package com.taro.common;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @Package: com.taro.tdevice.common
 * @File: DataSet.java
 * @Description: 分页数据对象
 * @Author: tanquanlong
 * @Date: 2016年12月9日
 * @Copyright taro(c)2010-2016
 */
public class DataSet<T> extends Message{

    protected List<T> content = Lists.newArrayList();

	public DataSet() {
	}
    
	public DataSet(int falg) {
		super(falg); 
	}
	
	public DataSet(List<T> content) {
		this.content=content;
	}
	
	public DataSet(int flag,String msg) {
		super(flag,msg);
	}
	

	public boolean hasContent() {
		return !content.isEmpty();
	}
	
    /**
     * 获得页内的记录列表.
     */
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    /**
     * 设置页内的记录列表.
     */
    public void setContent(final List<T> content) {
        this.content = content;
    }



}
