package com.taro.controller.pay;

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
import com.taro.entity.pay.TPaySettingEntity;
import com.taro.service.pay.TPaySettingService;

/**
 *<p>Title:TPaySettingController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:55:56
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/tPaySetting")
public class TPaySettingController extends AbstractController<TPaySettingEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "TPaySettingEntity";
	
	@Autowired
	private TPaySettingService tPaySettingService;
	
	@Override
    protected TPaySettingService getService () {
        return tPaySettingService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTPaySetting",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTPaySetting(@RequestBody TPaySettingEntity model) {
		
		Set<ConstraintViolation<TPaySettingEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTPaySetting(model);
		return new DataSetObject<TPaySettingEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}