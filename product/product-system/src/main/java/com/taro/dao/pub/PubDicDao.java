package com.taro.dao.pub;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pub.PubDicEntity;

/**
 *<p>Title:PubDicDao.java</p>
 *<p>Description:基础数据实例Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public interface PubDicDao extends AbstractDao<PubDicEntity> {

    List<PubDicEntity> listRelation(Map<String, Object> parameter);
    
    List<PubDicEntity> listByBase(Map<String, Object> parameter);
    
	int deleteAllByBasePid(Map<String, Object> parameter);
		
}