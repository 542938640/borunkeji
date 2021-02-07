package com.taro.controller.advert;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.advert.AdvertActivitiesEntity;
import com.taro.exception.BusinessException;
import com.taro.service.advert.AdvertActivitiesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:AdvertActivitiesController.java</p>
 *<p>Description:活动广告 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:23:49
 */
@Api(tags = "活动广告")
@RestController
@RequestMapping(value="/advertActivities")
public class AdvertActivitiesController extends AbstractController<AdvertActivitiesEntity> {

	public final static String MODULE = "活动广告";

	public final static String ENTITY = "AdvertActivitiesEntity";
	
	@Autowired
	private AdvertActivitiesService advertActivitiesService;
	
	@Override
    protected AdvertActivitiesService getService () {
        return advertActivitiesService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "page", value = "分页参数(页数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "rows", value = "分页参数(每页个条数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listAdvertActivities",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listAdvertActivities(HttpServletRequest request, 
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<AdvertActivitiesEntity> result=getService().list(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveAdvertActivities",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveAdvertActivities(HttpServletRequest request, @RequestBody AdvertActivitiesEntity model) {
		
		Set<ConstraintViolation<AdvertActivitiesEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveAdvertActivities(model);
		return new DataSetObject<AdvertActivitiesEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}


	@ApiOperation(value = "发布通知", notes = "发布通知")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/publish/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String publish(@PathVariable("id") String id) {
		getBefore(id);
		
		AdvertActivitiesEntity model=getService().publish(id);
		return new DataSetObject<AdvertActivitiesEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
}