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
import com.taro.entity.pay.PayUnionpayEntity;
import com.taro.exception.BusinessException;
import com.taro.service.pay.PayUnionpayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:PayUnionpayController.java</p>
 *<p>Description:银联支付组 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 11:15:33
 */
@Api(tags = "银联支付组")
@RestController
@RequestMapping(value="/payUnionpay")
public class PayUnionpayController extends AbstractController<PayUnionpayEntity> {

	public final static String MODULE = "银联支付组";

	public final static String ENTITY = "PayUnionpayEntity";
	
	@Autowired
	private PayUnionpayService payUnionpayService;
	
	@Override
    protected PayUnionpayService getService () {
        return payUnionpayService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listPayUnionpay",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listPayUnionpay(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<PayUnionpayEntity> result = getService().list(parameter);

		return new DataSet<PayUnionpayEntity>(result).toJson();
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/savePayUnionpay",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String savePayUnionpay(HttpServletRequest request, @RequestBody PayUnionpayEntity model) {
		
		Set<ConstraintViolation<PayUnionpayEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().savePayUnionpay(model);
		return new DataSetObject<PayUnionpayEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}