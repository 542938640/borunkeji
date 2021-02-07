package com.taro.controller.merchants;

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
import com.taro.entity.merchants.MerchantsCouponShareEntity;
import com.taro.service.merchants.MerchantsCouponShareService;

/**
 *<p>Title:MerchantsCouponShareController.java</p>
 *<p>Description:商户优惠券共享 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-12-17 19:22:13
 */
@Api(tags = "商户优惠券共享")
@RestController
@RequestMapping(value="/merchantsCouponShare")
public class MerchantsCouponShareController extends AbstractController<MerchantsCouponShareEntity> {

	public final static String MODULE = "商户优惠券共享";

	public final static String ENTITY = "MerchantsCouponShareEntity";
	
	@Autowired
	private MerchantsCouponShareService merchantsCouponShareService;
	
	@Override
    protected MerchantsCouponShareService getService () {
        return merchantsCouponShareService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMerchantsCouponShare",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMerchantsCouponShare(@RequestBody MerchantsCouponShareEntity model) {
		
		Set<ConstraintViolation<MerchantsCouponShareEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveMerchantsCouponShare(model);
		return new DataSetObject<MerchantsCouponShareEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}