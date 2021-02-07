package com.taro.controller.activiti;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.activiti.ProcessEntity;
import com.taro.exception.BusinessException;
import com.taro.service.activiti.ProcessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:ProcessController.java</p>
 *<p>Description:流程表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-01-20 16:55
 */
@Api(tags = "流程管理Controller")
@RestController
@RequestMapping(value="/process")
public class ProcessController extends AbstractController<ProcessEntity>{

	public final static String MODULE = "流程";

	public final static String ENTITY = "ProcessEntity";
	
	@Autowired
	private ProcessService processService;
	
	@Override
    protected ProcessService getService () {
        return processService;
    }

	@Override
    public String getModule() {
		return MODULE;
	}

	
	@ApiOperation(value = "保存流程信息", notes = "保存流程信息")
	@ApiImplicitParam(name = "model", value = "保存实体", required = true, dataType = "ProcessEntity", paramType = "query")
	@RequestMapping(value = "/saveProcess",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveProcess(@RequestBody ProcessEntity model) {
		
		Set<ConstraintViolation<ProcessEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveProcess(model);
		return new DataSetObject<ProcessEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "根据实例id获取流程详情", notes = "根据实例id获取流程详情")
	@ApiImplicitParam(name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/getProcess", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getProcess(@RequestParam String id) {
		getBefore(id);
		
		ProcessEntity model=getService().getProcess(id);
		return new DataSetObject<ProcessEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据实例ids删除多个实例", notes = "根据实例ids删除多个实例")
	@ApiImplicitParam(name = "ids", value = "实例ids", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/deleteProcess",method = RequestMethod.GET)
	public String deleteProcess(@RequestParam String ids) {
		deleteBefore(ids);
		int deleteRowCount = getService().deleteProcess(ids);
		return new Message(deleteRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"删除"+deleteRowCount+"条记录").toJson();
	}
}