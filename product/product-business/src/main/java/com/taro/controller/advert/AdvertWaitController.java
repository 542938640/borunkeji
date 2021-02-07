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
import com.taro.entity.advert.AdvertWaitEntity;
import com.taro.exception.BusinessException;
import com.taro.service.advert.AdvertWaitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:AdvertWaitController.java</p>
 *<p>Description:待机广告 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-02 11:22:08
 */
@Api(tags = "待机广告")
@RestController
@RequestMapping(value="/advertWait")
public class AdvertWaitController extends AbstractController<AdvertWaitEntity> {

	public final static String MODULE = "待机广告";

	public final static String ENTITY = "AdvertWaitEntity";
	
	@Autowired
	private AdvertWaitService advertWaitService;
	
	@Override
    protected AdvertWaitService getService () {
        return advertWaitService;
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
	@RequestMapping(value = "/listAdvertWait",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String list(HttpServletRequest request, 
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<AdvertWaitEntity> result=getService().list(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveAdvertWait",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveAdvertWait(HttpServletRequest request, @RequestBody AdvertWaitEntity model) {
		
		Set<ConstraintViolation<AdvertWaitEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveAdvertWait(model);
		return new DataSetObject<AdvertWaitEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "发布通知", notes = "发布通知")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/publish/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String publish(@PathVariable("id") String id) {
		getBefore(id);
		
		AdvertWaitEntity model=getService().publish(id);
		return new DataSetObject<AdvertWaitEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
}