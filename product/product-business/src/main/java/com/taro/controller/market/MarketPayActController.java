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
import com.taro.entity.market.MarketPayActEntity;
import com.taro.exception.BusinessException;
import com.taro.service.market.MarketPayActService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:MarketPayActController.java</p>
 *<p>Description:支付活动 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:32
 */
@Api(tags = "支付活动")
@RestController
@RequestMapping(value="/marketPayAct")
public class MarketPayActController extends AbstractController<MarketPayActEntity> {

	public final static String MODULE = "支付活动";

	public final static String ENTITY = "MarketPayActEntity";
	
	@Autowired
	private MarketPayActService marketPayActService;
	
	@Override
    protected MarketPayActService getService () {
        return marketPayActService;
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
	@RequestMapping(value = "/listMarketPayAct",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listMarketPayAct(HttpServletRequest request, 
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<MarketPayActEntity> result=getService().list(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketPayAct",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketPayAct(HttpServletRequest request, @RequestBody MarketPayActEntity model) {
		
		Set<ConstraintViolation<MarketPayActEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveMarketPayAct(model);
		return new DataSetObject<MarketPayActEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketPayActPrize",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketPayActPrize(HttpServletRequest request, @RequestBody MarketPayActEntity model) {
		createBefore(model);
		getService().saveMarketPayActPrize(model);
		return new DataSetObject<MarketPayActEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketPayActRule",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketPayActRule(HttpServletRequest request, @RequestBody MarketPayActEntity model) {
		createBefore(model);
		getService().saveMarketPayActRule(model);
		return new DataSetObject<MarketPayActEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

	@ApiOperation(value = "上线", notes = "上线")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/goOnline/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String publish(@PathVariable("id") String id) {
		getBefore(id);
		MarketPayActEntity model=getService().goOnline(id);
		return new DataSetObject<MarketPayActEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getRunByDevice/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getRunByDevice(@PathVariable("id") String id, String type) {
		getBefore(id);
		
		MarketPayActEntity model=getService().getRunByDevice(id, type);
		return new DataSetObject<MarketPayActEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}

	@ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getMarketPayAct/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String getMarketPayAct(@PathVariable("id") String id) {
		getBefore(id);
		
		MarketPayActEntity model=getService().getMarketPayAct(id);
		return new DataSetObject<MarketPayActEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_GET));
	}
	
}