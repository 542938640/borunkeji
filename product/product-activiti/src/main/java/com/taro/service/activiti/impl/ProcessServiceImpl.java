package com.taro.service.activiti.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taro.constants.Constant;
import com.taro.dao.activiti.ProcessDao;
import com.taro.entity.activiti.ProcessEntity;
import com.taro.entity.activiti.ProcessTaskEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.activiti.ProcessService;
import com.taro.service.activiti.ProcessTaskService;
import com.taro.utils.Utility;

/**
 *<p>Title:ProcessServiceImpl.java</p>
 *<p>Description:流程表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 16:55
 */
@Service
public class ProcessServiceImpl extends AbstractService<ProcessEntity> implements ProcessService{
	@Autowired
	private ProcessDao processDao;
    @Override
    protected ProcessDao getDao () {
        return processDao;
    }
	@Autowired
	private ProcessTaskService processTaskService;

    @Override
    public ProcessEntity saveProcess (ProcessEntity model) {
    	if(null == model) {
            throw new BusinessException("传入对象不能为空");
    	}
    	if(StringUtils.isBlank(model.getFlow_key())) {
            throw new BusinessException("传入流程ID不能为空");
    	}
    	if(StringUtils.isBlank(model.getName())) {
            throw new BusinessException("传入流程名称不能为空");
    	}
    	if(StringUtils.isBlank(model.getProcde_pid())) {
            throw new BusinessException("传入流程图不能为空");
    	}
    	if(StringUtils.isBlank(model.getDeployment_pid())) {
            throw new BusinessException("传入部署ID不能为空");
    	}
    	HashMap<String, Object> queryMap = new HashMap<String, Object>();
    	queryMap.put("flow_key", model.getFlow_key());
    	queryMap.put("sysFlag", "1");
    	List<ProcessEntity> queryList = getDao().list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
    		if(StringUtils.isBlank(model.getId()) || !model.getId().equals(queryList.get(0).getId())) {
                throw new BusinessException("流程ID已存在,请重新输入");
    		}
    	}
    	
    	//保存主表
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
        	//删除从表
        	if(StringUtils.isNotBlank(model.getIsDeleteTask()) && "1".equals(model.getIsDeleteTask())) {
        		processTaskService.deleteByProcess(model.getId());
        	}
    		super.update(model);
    	}
    	//保存任务表
    	if(CollectionUtils.isNotEmpty(model.getTaskList())) {
    		for(ProcessTaskEntity task : model.getTaskList()) {
    			if(StringUtils.isBlank(task.getId())) {
    				task.setProcess_pid(model.getId());//主表ID
    				processTaskService.save(task);
    			}else {
    				processTaskService.update(task);
    			}
    		}
    	}
        return model;
    }

    @Override
    public ProcessEntity getProcess (String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("传入对象id不能为空");
        }
        ProcessEntity model = getDao().get(id);
        if(null != model) {
        	HashMap<String, Object> queryMap = new HashMap<String, Object>();
        	queryMap.put("process_pid", id);
        	queryMap.put("sysFlag", "1");
        	List<ProcessTaskEntity> taskList = processTaskService.list(queryMap);
        	if(CollectionUtils.isNotEmpty(taskList)) {
        		model.setTaskList(taskList);
        	}
        }
        return model;
    }


    @Override
    public int deleteProcess (String ids) {
    	int num = super.deleteAll(ids);
    	if(StringUtils.isNotBlank(ids)) {
        	String[] idArr = ids.split(",");
        	if(null != idArr) {
        		for(String id : idArr) {
        			processTaskService.deleteByProcess(id);
        		}
        	}
    	}
        return num;
    }
}