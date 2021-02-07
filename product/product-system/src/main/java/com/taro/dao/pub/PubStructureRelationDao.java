package com.taro.dao.pub;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pub.PubStructureRelationEntity;

/**
 *<p>Title:PubStructureRelationDao.java</p>
 *<p>Description:结构关系Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:56
 */
public interface PubStructureRelationDao extends AbstractDao<PubStructureRelationEntity> {

    List<PubStructureRelationEntity> listMenu(Map<String, Object> parameter);
    
	int deleteAllByStructurePid(Map<String, Object> parameter);
}