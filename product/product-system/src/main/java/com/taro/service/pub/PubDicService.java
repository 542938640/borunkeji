package com.taro.service.pub;

import java.util.List;
import java.util.Map;

import com.taro.entity.pub.PubDicEntity;
import com.taro.service.IService;

/**
 *<p>Title:PubDicService.java</p>
 *<p>Description:基础数据实例Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
public interface PubDicService extends IService<PubDicEntity> {

    List<PubDicEntity> listRelation(Map<String, Object> parameter);
    
    List<PubDicEntity> listByBase(Map<String, Object> parameter);
    
	PubDicEntity savePubDic(PubDicEntity model);
	
    int deleteAllByBasePid(String ids);
	
}