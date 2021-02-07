package com.taro.service.sec;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.taro.common.Page;
import com.taro.entity.pub.PubDicEntity;
import com.taro.entity.pub.PubStructureRelationEntity;
import com.taro.entity.sec.SecUserEntity;
import com.taro.service.IService;

/**
 *<p>Title:SecUserService.java</p>
 *<p>Description:用户表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public interface SecUserService extends IService<SecUserEntity> {

    List<PubStructureRelationEntity> listMenu(Map<String, Object> parameter);

	PubDicEntity saveMenu(PubDicEntity model);

    int deleteMenu(String structureId, String structureRelationIds, String dicIds);
    
    Set<String> listPerm(String user_pid);

    Page<SecUserEntity> listSecUserByRole(int pageNum, int pageSize, Map<String, Object> parameter);
    
    Page<SecUserEntity> listSecUser(int pageNum, int pageSize, Map<String, Object> parameter);
    
	SecUserEntity saveSecUser(SecUserEntity model);

	SecUserEntity saveSecUserStatus(SecUserEntity model);

	SecUserEntity saveSecUserPassword(SecUserEntity model);
	
	SecUserEntity getByUserName(String username);
	
}