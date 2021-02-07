package com.taro.service.sec.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.dao.sec.SecUserDao;
import com.taro.entity.pub.PubDicEntity;
import com.taro.entity.pub.PubStructureRelationEntity;
import com.taro.entity.sec.SecUserEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pub.PubDicService;
import com.taro.service.pub.PubStructureRelationService;
import com.taro.service.sec.SecUserService;
import com.taro.utils.MD5Utils;
import com.taro.utils.SecurityMyUtils;
import com.taro.utils.UuidUtil;

/**
 *<p>Title:SecUserServiceImpl.java</p>
 *<p>Description:用户表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@Service
public class SecUserServiceImpl extends AbstractService<SecUserEntity> implements SecUserService{
	@Autowired
	private SecUserDao secUserDao;

	@Autowired
	private PubDicService pubDicService;

	@Autowired
	private PubStructureRelationService pubStructureRelationService;
	
    @Override
    protected SecUserDao getDao () {
        return secUserDao;
    }

    @Override
    public List<PubStructureRelationEntity> listMenu (Map<String, Object> parameter) {
    	if(null == parameter || !parameter.containsKey("user_pid")) {
            throw new BusinessException("用户id不能为空！");
    	}

    	List<PubStructureRelationEntity> treeList = new ArrayList<PubStructureRelationEntity>();
    	//根节点
    	PubStructureRelationEntity rootNode = new PubStructureRelationEntity();
    	rootNode.setId("-1");
    	rootNode.setChilddic_pid("-1");
    	rootNode.setChilddic_name("节点维护根");
    	rootNode.setChildbase_pid("-1");
    	rootNode.setChildbase_name("节点维护根");
        treeList.add(rootNode);
    	
    	parameter.put("sysFlag", "1");
        List<PubStructureRelationEntity> nodeList = getDao().listMenu(parameter);
        if(CollectionUtils.isNotEmpty(nodeList)) {
        	// 声明一个map，用来过滤已操作过的数据
            Map<String,String> map = Maps.newHashMapWithExpectedSize(nodeList.size());
            treeList.forEach(root -> getChild(root, nodeList, map));//传递根对象和一个空map
        }
        return treeList;
    }

    private void getChild(PubStructureRelationEntity rootNode, List<PubStructureRelationEntity> nodeList, Map<String,String> map){
        List<PubStructureRelationEntity> childList = Lists.newArrayList();
        nodeList.stream()
                .filter(node -> !map.containsKey(node.getId()))//节点id
                .filter(node -> node.getParentdic_pid().equals(rootNode.getChilddic_pid()))//子节点的父id==根节点的子id 继续循环
                .forEach(node -> {
                    map.put(node.getId(), node.getId());//节点id
                    getChild(node, nodeList, map);//递归调用
                    childList.add(node);
                });
        rootNode.setChildren(childList);
    }
    
    @Override
    public PubDicEntity saveMenu (PubDicEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空！");
    	}
    	boolean isAddRelation = StringUtils.isBlank(model.getId());
    	model = pubDicService.savePubDic(model);
    	
    	if(isAddRelation) {
        	PubStructureRelationEntity saveRelationBean = new PubStructureRelationEntity();
        	saveRelationBean.setStructure_pid(model.getStructure_pid());
        	saveRelationBean.setParentdic_pid(model.getParentdic_pid());
        	saveRelationBean.setChilddic_pid(model.getId());
        	pubStructureRelationService.save(saveRelationBean);
    	}
    	
        return model;
    }

    @Override
    public int deleteMenu (String structureId, String structureRelationIds, String dicIds) {
    	if(StringUtils.isBlank(structureId)) {
            throw new BusinessException("structureId不能为空！");
    	}
    	if(StringUtils.isBlank(structureRelationIds)) {
            throw new BusinessException("structureRelationIds不能为空！");
    	}
    	if(StringUtils.isBlank(dicIds)) {
            throw new BusinessException("dicIds不能为空！");
    	}
    	Map<String, Object> qeuryMap = new HashMap<String, Object>();
    	qeuryMap.put("structure_pid", structureId);
    	qeuryMap.put("dicIds", dicIds.split(Constant.COMMA));
    	List<PubStructureRelationEntity> queryList = pubStructureRelationService.list(qeuryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("存在拥有子节点的菜单，请先删除子菜单！");
    	}
    	
    	int num = pubStructureRelationService.deleteAll(structureRelationIds);
    	
    	pubDicService.deleteAll(dicIds);
    	
        return num;
    }

    @Override
    public Set<String> listPerm(String user_pid){
    	if(StringUtils.isBlank(user_pid)) {
            throw new BusinessException("用户id不能为空！");
    	}
    	Map<String, Object> queryMap = new HashMap<String, Object>();
    	queryMap.put("user_pid", user_pid);
    	return getDao().listPerm(queryMap);
    }
    
    @Override
    public Page<SecUserEntity> listSecUserByRole (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<SecUserEntity> reuslt = getDao().listSecUserByRole(parameter);
        Page<SecUserEntity> page = new Page<SecUserEntity>(reuslt);
        return page;
    }

    @Override
    public Page<SecUserEntity> listSecUser (int pageNum, int pageSize, Map<String, Object> parameter) {
        PageHelper.startPage(pageNum, pageSize, pageNum == 0 ? false : true);
        List<SecUserEntity> reuslt = getDao().listSecUser(parameter);
        Page<SecUserEntity> page = new Page<SecUserEntity>(reuslt);
        return page;
    }

    @Override
    public SecUserEntity saveSecUser (SecUserEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	Map<String, Object> queryMap = new HashMap<String, Object>();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("usernameEq", model.getUsername());
    	List<SecUserEntity> queryList = getDao().list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("用户名已存在,请重新编辑!");
    	}

//    	//激活照片
//    	if(StringUtils.isNotBlank(model.getPicture())) {
//    		fileManageService.activation(model.getPicture());
//    	}
    	
//    	//删除前端已删除的文件
//    	if(StringUtils.isNotBlank(model.getDeleteFileId())) {
//    		fileManageService.deleteFile(request, model.getDeleteFileId());
//    	}
    	
    	SecUserEntity saveBean = new SecUserEntity();
    	saveBean.setTenants_pid(model.getTenants_pid());
    	if(StringUtils.isBlank(model.getId())) {
    		String salt = UuidUtil.get32UUID();
    		
    		saveBean.setUsername(model.getUsername());
    		saveBean.setNickname(model.getNickname());
//    		saveBean.setSex(model.getSex());
//    		saveBean.setBirthday(model.getBirthday());
//    		saveBean.setPicture(model.getPicture());
//    		saveBean.setPhonenum(model.getPhonenum());
//    		saveBean.setEmail(model.getEmail());
    		saveBean.setSalt(salt);
    		saveBean.setPassword(MD5Utils.getSaltMD5(model.getPassword(), salt));
    		saveBean.setStatus(Constant.USERSTATUS1);
    		saveBean.setC1("0");
    		super.save(saveBean);
    	}else {
    		SecUserEntity oldBean = getDao().get(model.getId());
    		if(null == oldBean) {
                throw new BusinessException("用户查询错误,请确认!");
    		}
    		
    		saveBean.setId(model.getId());
    		saveBean.setUsername(model.getUsername());
    		saveBean.setNickname(model.getNickname());
    		saveBean.setPassword(MD5Utils.getSaltMD5(model.getPassword(), oldBean.getSalt()));
//    		saveBean.setSex(model.getSex());
//    		saveBean.setBirthday(model.getBirthday());
//    		saveBean.setPicture(model.getPicture());
//    		saveBean.setPhonenum(model.getPhonenum());
//    		saveBean.setEmail(model.getEmail());
    		super.update(saveBean);
    	}
    	
        return model;
    }

    @Override
    public SecUserEntity saveSecUserStatus (SecUserEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}

    	if(StringUtils.isBlank(model.getId())) {
            throw new BusinessException("用户ID不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException("用户状态不能为空!");
    	}
    	
    	SecUserEntity queryBean = getDao().get(model.getId());
    	if(null == queryBean) {
            throw new BusinessException("用户查询错误!");
    	}
    	SecUserEntity saveBean = new SecUserEntity();
    	saveBean.setId(model.getId());
    	saveBean.setStatus(model.getStatus());
    	super.update(saveBean);
        return model;
    }

    @Override
    public SecUserEntity saveSecUserPassword (SecUserEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}

    	if(StringUtils.isBlank(model.getOld_password())) {
            throw new BusinessException("现用密码不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getNew_password())) {
            throw new BusinessException("新密码不能为空!");
    	}
    	
    	SecUserEntity queryBean = getDao().get(SecurityMyUtils.getUserId());
    	if(null == queryBean) {
            throw new BusinessException("用户查询错误!");
    	}
    	
    	String salt = queryBean.getSalt();
    	String password = queryBean.getPassword();
    	
    	String old_password = MD5Utils.getSaltMD5(model.getOld_password(), salt);
    	
    	if(!password.equals(old_password)) {
            throw new BusinessException("现用密码错误!");
    	}
    	
    	SecUserEntity saveBean = new SecUserEntity();
    	saveBean.setId(queryBean.getId());
    	saveBean.setPassword(MD5Utils.getSaltMD5(model.getNew_password(), salt));
    	super.update(saveBean);
        return model;
    }

    @Override
    public SecUserEntity getByUserName(String username) {
		if(StringUtils.isBlank(username) || "null".equals(username)) {
			throw new BusinessException("用户名不能为空");
		}
		SecUserEntity user = getDao().getByUserName(username);
		return user;
    }
}