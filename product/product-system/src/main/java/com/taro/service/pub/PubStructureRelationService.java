package com.taro.service.pub;

import java.util.List;
import java.util.Map;

import com.taro.entity.pub.PubStructureRelationEntity;
import com.taro.service.IService;

/**
 *<p>Title:PubStructureRelationService.java</p>
 *<p>Description:结构关系Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:56
 */
public interface PubStructureRelationService extends IService<PubStructureRelationEntity> {

    List<PubStructureRelationEntity> listMenu(Map<String, Object> parameter);
    
    List<PubStructureRelationEntity> listTree(Map<String, Object> parameter);
    
	PubStructureRelationEntity savePubStructureRelation(PubStructureRelationEntity model);

    int deleteAllByStructurePid(String ids);

    int deleteAllByStructureDetailPid(String ids);
}