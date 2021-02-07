package com.taro.controller.market;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSet;
import com.taro.common.DataSetObject;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.market.TProductTypeEntity;
import com.taro.exception.BusinessException;
import com.taro.service.market.TProductTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:TProductTypeController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-22 21:18:31
 */
@Api(tags = "商品分类")
@RestController
@RequestMapping(value="/tProductType")
public class TProductTypeController extends AbstractController<TProductTypeEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "TProductTypeEntity";
	
	@Autowired
	private TProductTypeService tProductTypeService;
	
	@Override
    protected TProductTypeService getService () {
        return tProductTypeService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取分类树信息", notes = "获取分类树信息")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listTree",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listTree(@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<TProductTypeEntity> result = getService().listTree(parameter);

		return new DataSet<TProductTypeEntity>(result).toJson();
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTProductType",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTProductType(@RequestBody TProductTypeEntity model) {
		
		Set<ConstraintViolation<TProductTypeEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTProductType(model);
		return new DataSetObject<TProductTypeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}