package com.taro.service.pub;

import com.taro.service.IService;

import java.util.List;
import java.util.Map;

import com.taro.entity.pub.PubStructureDetailEntity;

/**
 *<p>Title:PubStructureDetailService.java</p>
 *<p>Description:结构节点关系信息Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:56
 */
public interface PubStructureDetailService extends IService<PubStructureDetailEntity> {

    List<PubStructureDetailEntity> listTopNode(Map<String, Object> parameter);
    
    List<PubStructureDetailEntity> listTree(Map<String, Object> parameter);

	PubStructureDetailEntity savePubStructureDetail(PubStructureDetailEntity model);

    int deletePubStructureDetail(String ids);
    
    int deleteAllByStructurePid(String ids);
}