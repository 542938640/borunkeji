package com.taro.controller.sec;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.taro.entity.sec.SecRolePermissionEntity;
import com.taro.exception.BusinessException;
import com.taro.service.sec.SecRolePermissionService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:SecRolePermissionController.java</p>
 *<p>Description:角色权限表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-23 18:02
 */
@RestController
@RequestMapping(value="/secRolePermission")
public class SecRolePermissionController extends AbstractController<SecRolePermissionEntity>{

	public final static String MODULE = "角色权限";

	public final static String ENTITY = "SecRolePermissionEntity";
	
	@Autowired
	private SecRolePermissionService secRolePermissionService;
	
	@Override
    protected SecRolePermissionService getService () {
        return secRolePermissionService;
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
	public String listNotPage(@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<SecRolePermissionEntity> result = getService().list(parameter);

		return new DataSet<SecRolePermissionEntity>(result).toJson();
	}
	

	@ApiOperation(value = "根据实例id保存详情", notes = "根据实例id保存详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveSecRolePermission",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveSecRolePermission(@RequestBody SecRolePermissionEntity model) {
		
		Set<ConstraintViolation<SecRolePermissionEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveSecRolePermission(model);
		return new DataSetObject<SecRolePermissionEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

}