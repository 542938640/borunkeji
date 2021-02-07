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
import com.taro.entity.merchants.MerchantsStoreEntity;
import com.taro.service.merchants.MerchantsStoreService;

/**
 *<p>Title:MerchantsStoreController.java</p>
 *<p>Description:商户门店 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-12-17 19:22:36
 */
@Api(tags = "商户门店")
@RestController
@RequestMapping(value="/merchantsStore")
public class MerchantsStoreController extends AbstractController<MerchantsStoreEntity> {

	public final static String MODULE = "商户门店";

	public final static String ENTITY = "MerchantsStoreEntity";
	
	@Autowired
	private MerchantsStoreService merchantsStoreService;
	
	@Override
    protected MerchantsStoreService getService () {
        return merchantsStoreService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMerchantsStore",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMerchantsStore(@RequestBody MerchantsStoreEntity model) {
		
		Set<ConstraintViolation<MerchantsStoreEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveMerchantsStore(model);
		return new DataSetObject<MerchantsStoreEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}