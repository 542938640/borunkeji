package com.taro.service.pub;

import com.taro.service.IService;

import java.util.List;
import java.util.Map;

import com.taro.entity.pub.PubBaseEntity;
import com.taro.entity.pub.PubStructureEntity;

/**
 *<p>Title:PubStructureService.java</p>
 *<p>Description:结构定义Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public interface PubStructureService extends IService<PubStructureEntity> {

    List<PubBaseEntity> listBaseAndAttribute(Map<String, Object> parameter);
    
	PubStructureEntity getPubStructure(String id);
    
    List<PubStructureEntity> listNotPage(Map<String, Object> parameter);
    
	PubStructureEntity savePubStructure(PubStructureEntity model);

    int deletePubStructure(String ids);
	
}