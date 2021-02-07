package com.taro.controller.market;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taro.common.DataSetObject;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.market.OrderExtEntity;
import com.taro.exception.BusinessException;
import com.taro.service.market.OrderExtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:OrderExtController.java</p>
 *<p>Description:订单扩展表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-29 16:25:37
 */
@Api(tags = "订单扩展表")
@RestController
@RequestMapping(value="/orderExt")
public class OrderExtController extends AbstractController<OrderExtEntity> {

	public final static String MODULE = "订单扩展表";

	public final static String ENTITY = "OrderExtEntity";
	
	@Autowired
	private OrderExtService orderExtService;
	
	@Override
    protected OrderExtService getService () {
        return orderExtService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listHomeNum",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listHomeNum(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		OrderExtEntity model = getService().listHomeNum(parameter);

		return new DataSetObject<OrderExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveOrderExt",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveOrderExt(@RequestBody OrderExtEntity model) {
		
		Set<ConstraintViolation<OrderExtEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveOrderExt(model);
		return new DataSetObject<OrderExtEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "page", value = "分页参数(页数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "rows", value = "分页参数(每页个条数)", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listOrder",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listOrder(@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter) throws ParseException{

		listBefore(parameter);
		Page<OrderExtEntity> result=getService().listOrder(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}

}