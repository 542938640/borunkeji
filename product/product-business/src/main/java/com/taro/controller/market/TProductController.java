package com.taro.controller.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.ConstraintViolation;
import com.taro.common.DataSetObject;
import com.taro.common.Page;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taro.exception.BusinessException;
import com.taro.constants.Constant;

import java.util.Map;
import java.util.Set;
import com.taro.controller.AbstractController;
import com.taro.entity.market.TProductEntity;
import com.taro.service.market.TProductService;

/**
 *<p>Title:TProductController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:17:41
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping(value="/tProduct")
public class TProductController extends AbstractController<TProductEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "TProductEntity";
	
	@Autowired
	private TProductService tProductService;
	
	@Override
    protected TProductService getService () {
        return tProductService;
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
	@RequestMapping(value = "/listProduct",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listProduct(@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<TProductEntity> result=getService().listProduct(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTProduct",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTProduct(@RequestBody TProductEntity model) {
		
		Set<ConstraintViolation<TProductEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTProduct(model);
		return new DataSetObject<TProductEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "保存商品启用状态", notes = "保存商品启用状态")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTProductEnable",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTProductEnable(@RequestBody TProductEntity model) {
		
		Set<ConstraintViolation<TProductEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTProductEnable(model);
		return new DataSetObject<TProductEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}