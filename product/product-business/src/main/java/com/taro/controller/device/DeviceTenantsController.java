package com.taro.controller.device;

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
import com.taro.entity.device.DeviceTenantsEntity;
import com.taro.service.device.DeviceTenantsService;

/**
 *<p>Title:DeviceTenantsController.java</p>
 *<p>Description:设备历史机构 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-01-10 16:48:16
 */
@Api(tags = "设备历史机构")
@RestController
@RequestMapping(value="/deviceTenants")
public class DeviceTenantsController extends AbstractController<DeviceTenantsEntity> {

	public final static String MODULE = "设备历史机构";

	public final static String ENTITY = "DeviceTenantsEntity";
	
	@Autowired
	private DeviceTenantsService deviceTenantsService;
	
	@Override
    protected DeviceTenantsService getService () {
        return deviceTenantsService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveDeviceTenants",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveDeviceTenants(@RequestBody DeviceTenantsEntity model) {
		
		Set<ConstraintViolation<DeviceTenantsEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveDeviceTenants(model);
		return new DataSetObject<DeviceTenantsEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}