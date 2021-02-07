package com.taro.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.exception.BusinessException;
import com.taro.service.IService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**  
 * @Package: com.taro.tdevice.controller  
 * @File: AbstractController.java   
 * @Description: TODO 
 * @Author: 张宇唯 
 * @Date: 2016年12月13日  
 * @Copyright taro(c)2010-2016
 */
public abstract class AbstractController<T> {

    public abstract String getModule();
	
	@Autowired
	protected Validator validator;
	
	protected abstract IService<T> getService();
	
	protected void listBefore(Map<String, Object> parameter){
		parameter=decode(parameter);
	};
	
	protected void createBefore(T model){
		
	};
	
	protected void getBefore(String id){
		
	};
	
	protected void deleteBefore(String id){
		
	};
	
	protected void updateBefore(String id,T model){
		
	};

	public Map<String, Object> decode(Map<String, Object> parameter){
		return parameter;
	}

	public String modelToJson(T model){
		return new DataSetObject<T>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	protected Map<String,PropertyPreFilter> ignoreAttr = new HashMap<String,PropertyPreFilter>();

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "page", value = "分页参数(页数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "rows", value = "分页参数(每页个条数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/list",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String list(@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<T> result=getService().list(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/get/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String get(@PathVariable("id") String id) {
		getBefore(id);
		
		T model=getService().get(id);
		return new DataSetObject<T>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "新增", notes = "新增")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "新增实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/save",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String create(@RequestBody T model) {
		
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().save(model);
		return new DataSetObject<T>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "更新", notes = "更新")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam (name = "model", value = "更新修改实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/update/{id}",produces = Constant.JSON_UTF_8, consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String update(@PathVariable("id") String id, @RequestBody T model) {
		if (id==null){
			throw new BusinessException("传入对象id不能为空");
		}
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		updateBefore(id,model);
		int updateRowCount=getService().update(model);
		return new Message(updateRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"更新"+updateRowCount+"条记录").toJson();
	}

	@ApiOperation(value = "根据id删除", notes = "根据id删除")
	@ApiImplicitParam(name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/delete/{id}",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id) {
		deleteBefore(id);

		int deleteRowCount=getService().delete(id);
		return new Message(deleteRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"删除"+deleteRowCount+"条记录").toJson();
	}

	@ApiOperation(value = "根据实例ids删除多个实例", notes = "根据实例ids删除多个实例")
	@ApiImplicitParam(name = "ids", value = "实例ids", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/deleteAll",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String deleteAll(@RequestParam String ids) {
		deleteBefore(ids);
		
		int deleteRowCount=getService().deleteAll(ids);
		return new Message(deleteRowCount>0?Constant.STATUS_OK:Constant.STATUS_ERROR_GENERAL,"删除"+deleteRowCount+"条记录").toJson();
	}
}
