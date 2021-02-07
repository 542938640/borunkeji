package com.taro.controller.pay;

import java.util.List;
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

import com.taro.common.DataSet;
import com.taro.common.DataSetObject;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.pay.PayUnionpayMerTerEntity;
import com.taro.exception.BusinessException;
import com.taro.service.pay.PayUnionpayMerTerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:PayUnionpayMerTerController.java</p>
 *<p>Description:银联商户终端号 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:16:04
 */
@Api(tags = "银联商户终端号")
@RestController
@RequestMapping(value="/payUnionpayMerTer")
public class PayUnionpayMerTerController extends AbstractController<PayUnionpayMerTerEntity> {

	public final static String MODULE = "银联商户终端号";

	public final static String ENTITY = "PayUnionpayMerTerEntity";
	
	@Autowired
	private PayUnionpayMerTerService payUnionpayMerTerService;
	
	@Override
    protected PayUnionpayMerTerService getService () {
        return payUnionpayMerTerService;
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
	@RequestMapping(value = "/listPayUnionpayMerTer",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listPayUnionpayMerTer(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<PayUnionpayMerTerEntity> result=getService().list(parameter);

		return new DataSet<PayUnionpayMerTerEntity>(result).toJson();
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/savePayUnionpayMerTer",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String savePayUnionpayMerTer(@RequestBody PayUnionpayMerTerEntity model) {
		
		Set<ConstraintViolation<PayUnionpayMerTerEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().savePayUnionpayMerTer(model);
		return new DataSetObject<PayUnionpayMerTerEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}