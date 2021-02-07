package com.taro.service.market.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taro.dao.market.TProductTypeDao;
import com.taro.entity.market.TProductTypeEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.market.TProductTypeService;
import com.taro.utils.SecurityMyUtils;

/**
 *<p>Title:TProductTypeServiceImpl.java</p>
 *<p>Description: ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:18:31
 */
@Service
public class TProductTypeServiceImpl extends AbstractService<TProductTypeEntity> implements TProductTypeService {
	
	@Autowired
	private TProductTypeDao tProductTypeDao;
	
    @Override
    protected TProductTypeDao getDao() {
        return tProductTypeDao;
    }

    @Override
    public List<TProductTypeEntity> listTree(Map<String, Object> parameter) {
    	List<TProductTypeEntity> treeList = new ArrayList<>();
    	//根节点
    	TProductTypeEntity rootNode = new TProductTypeEntity();
    	rootNode.setId("1001");
    	rootNode.setT_fname("所有分类");
        treeList.add(rootNode);
    	
        List<TProductTypeEntity> nodeList = getDao().list(parameter);
        if(CollectionUtils.isNotEmpty(nodeList)) {
        	//声明一个map，用来过滤已操作过的数据
            Map<String,String> map = Maps.newHashMapWithExpectedSize(nodeList.size());
            treeList.forEach(root -> getChild(root, nodeList, map));//传递根对象和一个空map
        }
        return treeList;
    }

    private void getChild(TProductTypeEntity rootNode, List<TProductTypeEntity> nodeList, Map<String,String> map){
        List<TProductTypeEntity> childList = Lists.newArrayList();
        nodeList.stream()
                .filter(node -> !map.containsKey(node.getId()))//map内不包含子节点的父id
                .filter(node -> String.valueOf(node.getT_fparentid()).equals(rootNode.getId()))//子节点的父id==根节点的子id 继续循环
                .forEach(node -> {
                    map.put(node.getId(), "1");//当前节点父id和节点id
                    getChild(node, nodeList, map);//递归调用
                    childList.add(node);
                });
        rootNode.setChildren(childList);
    }
    
    @Override
    public TProductTypeEntity saveTProductType (TProductTypeEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		model.setT_fcreateuser(SecurityMyUtils.getUserId());
    		model.setT_fcreatedate(new Date());
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }
    
}