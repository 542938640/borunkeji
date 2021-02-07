package com.taro.dao.pub;

import java.util.List;
import java.util.Map;

import com.taro.dao.AbstractDao;
import com.taro.entity.pub.PubStructureDetailEntity;

/**
 *<p>Title:PubStructureDetailDao.java</p>
 *<p>Description:结构节点关系信息Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:56
 */
public interface PubStructureDetailDao extends AbstractDao<PubStructureDetailEntity> {

    List<PubStructureDetailEntity> listTopNode(Map<String, Object> parameter);
    
	int deleteAllByStructurePid(Map<String, Object> parameter);
}