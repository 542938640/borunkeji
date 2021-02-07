package com.taro.controller.market;

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
import com.taro.entity.market.MarketLuckDrawEntity;
import com.taro.exception.BusinessException;
import com.taro.service.market.MarketLuckDrawService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:MarketLuckDrawController.java</p>
 *<p>Description:幸运抽奖 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 10:06:13
 */
@Api(tags = "幸运抽奖")
@RestController
@RequestMapping(value="/marketLuckDraw")
public class MarketLuckDrawController extends AbstractController<MarketLuckDrawEntity> {

	public final static String MODULE = "幸运抽奖";

	public final static String ENTITY = "MarketLuckDrawEntity";
	
	@Autowired
	private MarketLuckDrawService marketLuckDrawService;
	
	@Override
    protected MarketLuckDrawService getService () {
        return marketLuckDrawService;
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
	@RequestMapping(value = "/listMarketLuckDraw",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listMarketLuckDraw(HttpServletRequest request, 
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<MarketLuckDrawEntity> result=getService().list(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketLuckDraw",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketLuckDraw(HttpServletRequest request, @RequestBody MarketLuckDrawEntity model) {
		
		Set<ConstraintViolation<MarketLuckDrawEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveMarketLuckDraw(model);
		return new DataSetObject<MarketLuckDrawEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketLuckDrawPrize",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketLuckDrawPrize(HttpServletRequest request, @RequestBody MarketLuckDrawEntity model) {
		createBefore(model);
		getService().saveMarketLuckDrawPrize(model);
		return new DataSetObject<MarketLuckDrawEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketLuckDrawRule",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketLuckDrawRule(HttpServletRequest request, @RequestBody MarketLuckDrawEntity model) {
		createBefore(model);
		getService().saveMarketLuckDrawRule(model);
		return new DataSetObject<MarketLuckDrawEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "上线活动", notes = "上线活动")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/goOnline/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String publish(@PathVariable("id") String id) {
		getBefore(id);
		MarketLuckDrawEntity model=getService().goOnline(id);
		return new DataSetObject<MarketLuckDrawEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getRunByDevice/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getRunByDevice(@PathVariable("id") String id) {
		getBefore(id);
		
		MarketLuckDrawEntity model=getService().getRunByDevice(id);
		return new DataSetObject<MarketLuckDrawEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getMarketLuckDraw/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getMarketLuckDraw(@PathVariable("id") String id) {
		getBefore(id);
		
		MarketLuckDrawEntity model=getService().getMarketLuckDraw(id);
		return new DataSetObject<MarketLuckDrawEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
}