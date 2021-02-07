package com.taro.service;

import com.taro.common.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by lzq on 2017/5/10.
 */
public interface IService<T> {
    T save(T model);

    int update(T model);

    T get(String id);

    int delete(String id);

    Page<T> list(int pageNum, int pageSize, Map<String, Object> parameter);

    List<T> list(Map<String, Object> parameter);

    T get(Map<String, Object> parameter);

    int deleteAll(String ids);

    public List<T> insertObjects(List<T> dateList);

    public int batchUpdate(List<T> dateList);

    public int batchUpdates(List<T> dateList);
}
