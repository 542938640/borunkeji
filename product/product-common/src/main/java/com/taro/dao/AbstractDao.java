package com.taro.dao;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.taro.tdevice.dao
 * @File: AbstractDao.java
 * @Description: TODO
 * @Author: tanquanlong
 * @Date: 2016年12月13日
 * @Copyright taro(c)2010-2016
 */
public interface AbstractDao<T> {
    Long insert(T entity);

    int update(T entity);

    int delete(Map<String, Object> parameter);

    T get(String id);

    T get(Map<String, Object> parameter);

    List<T> list(Map<String, Object> parameter);

    int update(List<String> list);

    int deleteAll(Map<String, Object> parameter);

    List<T> insertObjects(List<T> list);

    int batchUpdate(List<T> list);

}
