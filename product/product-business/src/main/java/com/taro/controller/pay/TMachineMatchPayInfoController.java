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
import com.taro.entity.pay.TMachineMatchPayInfoEntity;
import com.taro.service.pay.TMachineMatchPayInfoService;

/**
 *<p>Title:TMachineMatchPayInfoController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-21 10:56:19
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/tMachineMatchPayInfo")
public class TMachineMatchPayInfoController extends AbstractController<TMachineMatchPayInfoEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "TMachineMatchPayInfoEntity";
	
	@Autowired
	private TMachineMatchPayInfoService tMachineMatchPayInfoService;
	
	@Override
    protected TMachineMatchPayInfoService getService () {
        return tMachineMatchPayInfoService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveTMachineMatchPayInfo",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveTMachineMatchPayInfo(@RequestBody TMachineMatchPayInfoEntity model) {
		
		Set<ConstraintViolation<TMachineMatchPayInfoEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveTMachineMatchPayInfo(model);
		return new DataSetObject<TMachineMatchPayInfoEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}