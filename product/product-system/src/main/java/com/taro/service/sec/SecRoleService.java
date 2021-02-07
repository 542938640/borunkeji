package com.taro.service.sec;

import java.util.List;
import java.util.Map;

import com.taro.entity.sec.SecRoleEntity;
import com.taro.service.IService;

/**
 *<p>Title:SecRoleService.java</p>
 *<p>Description:角色表Service</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
public interface SecRoleService extends IService<SecRoleEntity> {
	
	SecRoleEntity saveSecRole(SecRoleEntity model);

    List<SecRoleEntity> listTree(Map<String, Object> parameter);
}