package com.taro.controller.sec;

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
import com.taro.entity.sec.SecTenantsPermissionEntity;
import com.taro.exception.BusinessException;
import com.taro.service.sec.SecTenantsPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:SecTenantsPermissionController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-11 23:14:15
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/secTenantsPermission")
public class SecTenantsPermissionController extends AbstractController<SecTenantsPermissionEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "SecTenantsPermissionEntity";
	
	@Autowired
	private SecTenantsPermissionService secTenantsPermissionService;
	
	@Override
    protected SecTenantsPermissionService getService () {
        return secTenantsPermissionService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}

	@ApiOperation(value = "获取列表数据", notes = "获取列表数据")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "parameter", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")
	})
	@RequestMapping(value = "/listNotPage",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)
	public String listNotPage(HttpServletRequest request, @RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<SecTenantsPermissionEntity> result = getService().list(parameter);

		return new DataSet<SecTenantsPermissionEntity>(result).toJson();
	}

	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveSecTenantsPermission",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveSecTenantsPermission(HttpServletRequest request, @RequestBody SecTenantsPermissionEntity model) {
		
		Set<ConstraintViolation<SecTenantsPermissionEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveSecTenantsPermission(model);
		return new DataSetObject<SecTenantsPermissionEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}