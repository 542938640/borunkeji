package com.taro.service.pub.impl;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;import org.apache.commons.collections4.CollectionUtils;import org.apache.commons.lang3.StringUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import com.google.common.collect.Lists;import com.google.common.collect.Maps;import com.taro.dao.pub.PubBaseDao;import com.taro.entity.pub.PubBaseAttributeEntity;import com.taro.entity.pub.PubBaseEntity;import com.taro.exception.BusinessException;import com.taro.service.AbstractService;import com.taro.service.pub.PubBaseAttributeService;import com.taro.service.pub.PubBaseService;import com.taro.service.pub.PubDicService;/** *<p>Title:PubBaseServiceImpl.java</p> *<p>Description:基础数据类型ServiceImpl</p> *@author 张宇唯 *@version 1.0 *@Automatically generate by Coder in 2019-07-31 10:57 */@Servicepublic class PubBaseServiceImpl extends AbstractService<PubBaseEntity> implements PubBaseService{	@Autowired	private PubBaseDao pubBaseDao;		@Autowired	private PubBaseAttributeService pubBaseAttributeService;	@Autowired	private PubDicService pubDicService;	    @Override    protected PubBaseDao getDao () {        return pubBaseDao;    }    @Override    public List<PubBaseEntity> listTree (Map<String, Object> parameter) {    	List<PubBaseEntity> treeList = new ArrayList<PubBaseEntity>();    	//根节点    	PubBaseEntity rootNode = new PubBaseEntity();    	rootNode.setId("-1");    	rootNode.setName("基础数据类型树");        treeList.add(rootNode);    	    	parameter.put("sysFlag", "1");        List<PubBaseEntity> nodeList = getDao().list(parameter);        if(CollectionUtils.isNotEmpty(nodeList)) {        	//声明一个map，用来过滤已操作过的数据            Map<String,String> map = Maps.newHashMapWithExpectedSize(nodeList.size());            treeList.forEach(root -> getChild(root, nodeList, map));//传递根对象和一个空map        }        return treeList;    }    private void getChild(PubBaseEntity rootNode, List<PubBaseEntity> nodeList, Map<String,String> map){        List<PubBaseEntity> childList = Lists.newArrayList();        nodeList.stream()                .filter(node -> !map.containsKey(node.getId()))//map内不包含子节点的父id                .filter(node -> node.getParent_pid().equals(rootNode.getId()))//子节点的父id==根节点的子id 继续循环                .forEach(node -> {                    map.put(node.getId(), "1");//当前节点父id和节点id                    getChild(node, nodeList, map);//递归调用                    childList.add(node);                });        rootNode.setChildren(childList);    }        @Override    public PubBaseEntity savePubBase (PubBaseEntity model) {    	if(null == model) {            throw new BusinessException("保存对象不能为空!");    	}    	    	Map<String, Object> queryCodeMap = new HashMap<String, Object>();    	queryCodeMap.put("not_id", model.getId());    	queryCodeMap.put("sysFlag", "1");    	queryCodeMap.put("codeEq", model.getCode());    	List<PubBaseEntity> queryCodeList = getDao().list(queryCodeMap);    	if(CollectionUtils.isNotEmpty(queryCodeList)) {            throw new BusinessException("编码[" + model.getCode() + "]已存在,请重新编辑!");    	}    	Map<String, Object> queryNameMap = new HashMap<String, Object>();    	queryNameMap.put("not_id", model.getId());    	queryNameMap.put("sysFlag", "1");    	queryNameMap.put("nameEq", model.getName());    	List<PubBaseEntity> queryNameList = getDao().list(queryNameMap);    	if(CollectionUtils.isNotEmpty(queryNameList)) {            throw new BusinessException("名称[" + model.getName() + "]已存在,请重新编辑!");    	}    	    	if(StringUtils.isBlank(model.getId())) {    		super.save(model);    	}else {    		super.update(model);    	}    	    	if(CollectionUtils.isNotEmpty(model.getAttributeList())) {    		for(PubBaseAttributeEntity attribute : model.getAttributeList()) {    			attribute.setBase_pid(model.getId());    	    	if(StringUtils.isBlank(attribute.getId())) {    	    		pubBaseAttributeService.save(attribute);    	    	}else {    	    		pubBaseAttributeService.update(attribute);    	    	}    		}    	}    	        return model;    }    public int deletePubBase (String ids) {    	int num = super.deleteAll(ids);    	    	if(StringUtils.isNotBlank(ids)) {    		pubBaseAttributeService.deleteAllByBasePid(ids);    		pubDicService.deleteAllByBasePid(ids);    	}    	        return num;    }    public PubBaseEntity getByName(String name) {    	if(StringUtils.isBlank(name)) {    		return null;    	}    	Map<String, Object> queryMap = new HashMap<String, Object>();    	queryMap.put("sysFlag", "1");    	queryMap.put("name", name);    	List<PubBaseEntity> queryList = getDao().list(queryMap);    	if(CollectionUtils.isNotEmpty(queryList)) {    		return queryList.get(0);    	}    	return null;    }}