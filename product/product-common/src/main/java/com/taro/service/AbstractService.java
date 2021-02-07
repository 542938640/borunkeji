package com.taro.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.github.pagehelper.PageHelper;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.dao.AbstractDao;
import com.taro.entity.AbstractEntity;
import com.taro.entity.ProcessDataEntity;
import com.taro.exception.BusinessException;
import com.taro.utils.SecurityMyUtils;
import com.taro.utils.Utility;

/**
 * @Package: com.taro.tdevice.service
 * @File: AbstractService.java
 * @Description: TODO
 * @Author: tanquanlong
 * @Date: 2016年12月13日
 * @Copyright taro(c)2010-2016
 */
public abstract class AbstractService<T> implements IService<T> {

    protected abstract AbstractDao<T> getDao ();
    
    public void workFlowCallback(ProcessDataEntity processData){}
    
    public T save (T model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }
        if (model instanceof AbstractEntity) {
            ((AbstractEntity) model).reflash(true);
        }
        getDao().insert(model);
        return model;
    }

    public int update (T model) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }

        if (model instanceof AbstractEntity) {
            ((AbstractEntity) model).reflash(false);
        }
        return getDao().update(model);
    }

    public T get (String id) {
        if (id == null) {
            throw new BusinessException("传入对象id不能为空");
        }
        return getDao().get(id);
    }

    
    public int delete (String id) {
        if (id == null) {
            throw new BusinessException("传入对象id不能为空");
        }
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", id);
        String currentDate = DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS);
        parameter.put("lastModifiedTime", currentDate);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            String userId = SecurityMyUtils.getUserId();
            if (userId != null) {
                parameter.put("lastModifier", userId);
            }
        }

        return getDao().delete(parameter);
    }

    public Page<T> list (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<T> reuslt = getDao().list(parameter);
        Page<T> page = new Page<T>(reuslt);
        return page;
    }

    public List<T> list (Map<String, Object> parameter) {
        List<T> reuslt = getDao().list(parameter);
        return reuslt;
    }

    public T get (Map<String, Object> parameter) {
        if (parameter.isEmpty()) {
            throw new BusinessException("传入对象parameter不能为空");
        }
        return getDao().get(parameter);
    }

    public int deleteAll (String ids) {
        //校验参数
        Utility.raiseIfWrong(ids, "ids不能为空");

        String[] pids = ids.split(Constant.COMMA);
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("ids", pids);
        String currentDate = DateFormatUtils.format(new Date(), Constant.DF_DATE_YYYYMMDDHHMMSS);
        parameter.put("lastModifiedTime", currentDate);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            String userId = SecurityMyUtils.getUserId();
            if (userId != null) {
                parameter.put("lastModifier", userId);
            }
        }
        return getDao().deleteAll(parameter);
    }


    /**
     * 批量新增设备数据类型属性
     *
     * @param dateList
     * @return
     */
    @Override
    public List<T> insertObjects (List<T> dateList) {
        return getDao().insertObjects(dateList);
    }

    /**
     * 批量修改设备数据类型属性
     *
     * @param dateList
     * @return
     */
    @Override
    public int batchUpdate (List<T> dateList) {
        return getDao().batchUpdate(dateList);
    }


    /**
     * 批量修改实体属性
     * @param dateList 实体集合
     * @return 受影响结果行数
     */
    @Override
    public int batchUpdates(List<T> dateList) {
        int res = 0;
        for(T model : dateList) {
            res += this.update(model);
        }
        return res;
    }
}
