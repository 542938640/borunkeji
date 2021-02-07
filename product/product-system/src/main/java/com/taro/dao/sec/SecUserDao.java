package com.taro.dao.sec;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.taro.dao.AbstractDao;
import com.taro.entity.pub.PubStructureRelationEntity;
import com.taro.entity.sec.SecUserEntity;

/**
 *<p>Title:SecUserDao.java</p>
 *<p>Description:用户表Dao</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public interface SecUserDao extends AbstractDao<SecUserEntity> {

    List<PubStructureRelationEntity> listMenu(Map<String, Object> parameter);

    Set<String> listPerm(Map<String, Object> parameter);
    
    List<SecUserEntity> listSecUserByRole(Map<String, Object> parameter);
    
    List<SecUserEntity> listSecUser(Map<String, Object> parameter);
    
	SecUserEntity getByUserName(String username);
}