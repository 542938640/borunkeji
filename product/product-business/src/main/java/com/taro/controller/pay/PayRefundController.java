package com.taro.controller.pay;

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
import com.taro.common.Message;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.pay.PayRefundEntity;
import com.taro.exception.BusinessException;
import com.taro.service.pay.PayRefundService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:PayRefundController.java</p>
 *<p>Description:退款申请 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-27 14:34:57
 */
@Api(tags = "退款申请")
@RestController
@RequestMapping(value="/payRefund")
public class PayRefundController extends AbstractController<PayRefundEntity> {

	public final static String MODULE = "退款申请";

	public final static String ENTITY = "PayRefundEntity";
	
	@Autowired
	private PayRefundService payRefundService;
	
	@Override
    protected PayRefundService getService () {
        return payRefundService;
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
	@RequestMapping(value = "/listPayRefund",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listPayRefund(HttpServletRequest request,
			@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		Page<PayRefundEntity> result = getService().listPayRefund(pageNum,pageSize,parameter);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/savePayRefund",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String savePayRefund(@RequestBody PayRefundEntity model) {
		
		Set<ConstraintViolation<PayRefundEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().savePayRefund(model);
		return new DataSetObject<PayRefundEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "提交退款申请", notes = "提交退款申请")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveRefundApply",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMerchantsStore(HttpServletRequest request, @RequestBody PayRefundEntity model) {
		PayRefundEntity payRefund = getService().startAutoWorkFlow(model);
		return new DataSetObject<PayRefundEntity>(payRefund).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
	@ApiOperation(value = "审核", notes = "审核")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/check",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String check(HttpServletRequest request, @RequestBody PayRefundEntity model) {
		getService().checkAutoWorkFlow(model);
		return new Message(Constant.STATUS_OK, "提交成功").toJson();
	}

	@ApiOperation(value = "保存受理状态", notes = "保存受理状态")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveStatus",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveStatus(@RequestBody PayRefundEntity model) {
		getService().saveStatus(model);
		return new DataSetObject<PayRefundEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}