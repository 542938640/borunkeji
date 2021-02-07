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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.taro.exception.BusinessException;
import com.taro.constants.Constant;
import java.util.Set;
import com.taro.controller.AbstractController;
import com.taro.entity.market.TOrderInfoEntity;
import com.taro.service.market.TOrderInfoService;

/**
 *<p>Title:TOrderInfoController.java</p>
 *<p>Description:订单信息表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-28 20:33:44
 */
@Api(tags = "订单信息表")
@RestController
@RequestMapping(value="/tOrderInfo")
public class TOrderInfoController extends AbstractController<TOrderInfoEntity> {

	public final static String MODULE = "订单信息表";

	public final static String ENTITY = "TOrderInfoEntity";
	
	@Autowired
	private TOrderInfoService tOrderInfoService;
	
	@Override
    protected TOrderInfoService getService () {
        return tOrderInfoService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTOrderInfo",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTOrderInfo(@RequestBody TOrderInfoEntity model) {
		
		Set<ConstraintViolation<TOrderInfoEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTOrderInfo(model);
		return new DataSetObject<TOrderInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}