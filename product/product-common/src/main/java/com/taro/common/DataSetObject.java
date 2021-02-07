package com.taro.common;

/**
 * @Package: com.taro.tdevice.common
 * @File: DataSetObject.java
 * @Description: 分页数据对象
 * @Author: tanquanlong
 * @Date: 2016年12月9日
 * @Copyright taro(c)2010-2016
 */
public class DataSetObject<T> extends Message {
	
    // -- 返回结果 --//
	private T content = null;
	
	public DataSetObject() {
	}

    public DataSetObject(int falg) {
        super(falg);
    }

    public DataSetObject(T content) {
        this.content=content;
    }

    public DataSetObject(int flag,String msg) {
        super(flag,msg);
    }


    public boolean hasContent() {
        return content != null;
    }

    /**
     * 获得页内的记录列表.
     */
    public T getContent() {
        return content;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setContent(final T content) {
        this.content = content;
    }
}
