package com.taro.controller.advert;

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
import com.taro.entity.advert.AdvertEndDetailEntity;
import com.taro.service.advert.AdvertEndDetailService;

/**
 *<p>Title:AdvertEndDetailController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-11-05 19:59:54
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/advertEndDetail")
public class AdvertEndDetailController extends AbstractController<AdvertEndDetailEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "AdvertEndDetailEntity";
	
	@Autowired
	private AdvertEndDetailService advertEndDetailService;
	
	@Override
    protected AdvertEndDetailService getService () {
        return advertEndDetailService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveAdvertEndDetail",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveAdvertEndDetail(@RequestBody AdvertEndDetailEntity model) {
		
		Set<ConstraintViolation<AdvertEndDetailEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveAdvertEndDetail(model);
		return new DataSetObject<AdvertEndDetailEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}