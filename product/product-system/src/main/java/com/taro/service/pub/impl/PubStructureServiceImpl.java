package com.taro.service.pub.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.dao.pub.PubStructureDao;
import com.taro.entity.pub.PubBaseEntity;
import com.taro.entity.pub.PubStructureDetailEntity;
import com.taro.entity.pub.PubStructureEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pub.PubBaseAttributeService;
import com.taro.service.pub.PubStructureDetailService;
import com.taro.service.pub.PubStructureRelationService;
import com.taro.service.pub.PubStructureService;

/**
 *<p>Title:PubStructureServiceImpl.java</p>
 *<p>Description:结构定义ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
@Service
public class PubStructureServiceImpl extends AbstractService<PubStructureEntity> implements PubStructureService{
	@Autowired
	private PubStructureDao pubStructureDao;

	@Autowired
	private PubStructureDetailService pubStructureDetailService;

	@Autowired
	private PubStructureRelationService pubStructureRelationService;

	@Autowired
	private PubBaseAttributeService pubBaseAttributeService;
	
    @Override
    protected PubStructureDao getDao () {
        return pubStructureDao;
    }

    @Override
    public List<PubBaseEntity> listBaseAndAttribute(Map<String, Object> parameter){
    	if(null == parameter) {
            throw new BusinessException("查询对象为空");
    	}
    	String parentbase_pid = String.valueOf(parameter.get("parentbase_pid"));
    	if(StringUtils.isBlank(parentbase_pid) || "null".equals(parentbase_pid)) {
            throw new BusinessException("父节点不能为空");
    	}
    	String structure_pid = String.valueOf(parameter.get("structure_pid"));
    	if(StringUtils.isBlank(structure_pid) || "null".equals(structure_pid)) {
            throw new BusinessException("结构id不能为空");
    	}
    	List<PubBaseEntity> baseList = new ArrayList<PubBaseEntity>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<PubStructureDetailEntity> detailList = null;
		queryMap.put("structure_pid", structure_pid);
		queryMap.put("sysFlag", "1");
    	//代表查最顶层的节点
    	if("-1".equals(parentbase_pid)) {
    		detailList = pubStructureDetailService.listTopNode(parameter);
    		if(CollectionUtils.isEmpty(detailList)) {
    			queryMap.put("equal", 1);
    			detailList = pubStructureDetailService.list(queryMap);
    		}
    	}else {
			queryMap.put("parentbase_pid", parentbase_pid);
    		detailList = pubStructureDetailService.list(queryMap);
		}
    	
    	if(CollectionUtils.isNotEmpty(detailList)) {
    		PubBaseEntity base = null;
    		for(PubStructureDetailEntity detail : detailList) {
    			base = new PubBaseEntity();
    			base.setId(detail.getChildbase_pid());
    			base.setName(detail.getChildbase_name());
    			queryMap.clear();
    			queryMap.put("base_pid", detail.getChildbase_pid());
    			queryMap.put("sysFlag", "1");
    			base.setAttributeList(pubBaseAttributeService.list(queryMap));
    			baseList.add(base);
    		}
    	}
    	
    	return baseList;
    }
    
    @Override
    public PubStructureEntity getPubStructure (String id) {
        if (id == null) {
            throw new BusinessException("传入对象id不能为空");
        }
        PubStructureEntity model = getDao().get(id);
        
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("structure_pid", id);
        queryMap.put("sysFlag", 1);
        model.setDetailList(pubStructureDetailService.list(queryMap));
        
        return model;
    }

    @Override
    public List<PubStructureEntity> listNotPage (Map<String, Object> parameter) {
    	parameter.put("sysFlag", "1");
        List<PubStructureEntity> reuslt = getDao().list(parameter);
        return reuslt;
    }

    @Override
    public PubStructureEntity savePubStructure (PubStructureEntity model) {
    	if(null == model) {
            throw new BusinessException("保存对象不能为空!");
    	}
    	
    	Map<String, Object> queryMap = new HashMap<String, Object>();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("codeEq", model.getCode());
    	List<PubStructureEntity> queryList = getDao().list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("编码已存在,请重新编辑!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
    	if(StringUtils.isNotBlank(model.getDetailDeleteIds())) {
    		pubStructureDetailService.deleteAll(model.getDetailDeleteIds());
    	}
    	
    	if(CollectionUtils.isNotEmpty(model.getDetailList())) {
    		for(PubStructureDetailEntity detail : model.getDetailList()) {
    			detail.setStructure_pid(model.getId());
    	    	if(StringUtils.isBlank(detail.getId())) {
    	    		pubStructureDetailService.save(detail);
    	    	}else {
    	    		pubStructureDetailService.update(detail);
    	    	}
    		}
    	}
    	
        return model;
    }

    @Override
    public int deletePubStructure (String ids) {
    	int num = super.deleteAll(ids);
    	
    	if(StringUtils.isNotBlank(ids)) {
    		pubStructureDetailService.deleteAllByStructurePid(ids);
    		pubStructureRelationService.deleteAllByStructurePid(ids);
    	}
    	
        return num;
    }

}