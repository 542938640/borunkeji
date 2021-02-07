package com.taro.service.sec.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taro.dao.sec.SecResouceDao;
import com.taro.entity.sec.SecResouceEntity;
import com.taro.service.sec.SecResouceService;
import com.taro.service.AbstractService;

/**
 *<p>Title:SecResouceServiceImpl.java</p>
 *<p>Description:资源表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:08
 */
@Service
public class SecResouceServiceImpl extends AbstractService<SecResouceEntity> implements SecResouceService{
	@Autowired
	private SecResouceDao secResouceDao;
    @Override
    protected SecResouceDao getDao () {
        return secResouceDao;
    }
    
}