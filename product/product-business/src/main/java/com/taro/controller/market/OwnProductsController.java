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
import com.taro.entity.market.OwnProductsEntity;
import com.taro.service.market.OwnProductsService;

/**
 *<p>Title:OwnProductsController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-26 23:48:00
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/ownProducts")
public class OwnProductsController extends AbstractController<OwnProductsEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "OwnProductsEntity";
	
	@Autowired
	private OwnProductsService ownProductsService;
	
	@Override
    protected OwnProductsService getService () {
        return ownProductsService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveOwnProducts",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveOwnProducts(@RequestBody OwnProductsEntity model) {
		
		Set<ConstraintViolation<OwnProductsEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveOwnProducts(model);
		return new DataSetObject<OwnProductsEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}