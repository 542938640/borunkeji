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
import com.taro.entity.sec.SecUserRoleRelEntity;
import com.taro.exception.BusinessException;
import com.taro.service.sec.SecUserRoleRelService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *<p>Title:SecuserRoleRelController.java</p>
 *<p>Description:用户角色关系表 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@RestController
@RequestMapping(value="/secUserRoleRel")
public class SecUserRoleRelController extends AbstractController<SecUserRoleRelEntity>{

	public final static String MODULE = "用户角色关系";

	public final static String ENTITY = "SecUserRoleRelEntity";
	
	@Autowired
	private SecUserRoleRelService secUserRoleRelService;
	
	@Override
    protected SecUserRoleRelService getService () {
        return secUserRoleRelService;
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
	public String list(@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		List<SecUserRoleRelEntity> result = getService().list(parameter);

		return new DataSet<SecUserRoleRelEntity>(result).toJson();
	}

	@ApiOperation(value = "根据实例id保存详情", notes = "根据实例id保存详情")
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = "T", paramType = "query")
	})
	@RequestMapping(value = "/saveSecUserRoleRel",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveSecUserRoleRel(@RequestBody SecUserRoleRelEntity model) {
		
		Set<ConstraintViolation<SecUserRoleRelEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveSecUserRoleRel(model);
		return new DataSetObject<SecUserRoleRelEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}

}