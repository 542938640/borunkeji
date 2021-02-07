package com.taro.controller.finance;

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
import com.taro.entity.finance.FinanceNoteDetailEntity;
import com.taro.service.finance.FinanceNoteDetailService;

/**
 *<p>Title:FinanceNoteDetailController.java</p>
 *<p>Description: Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2021-02-05 17:05:55
 */
@Api(tags = "")
@RestController
@RequestMapping(value="/financeNoteDetail")
public class FinanceNoteDetailController extends AbstractController<FinanceNoteDetailEntity> {

	public final static String MODULE = "";

	public final static String ENTITY = "FinanceNoteDetailEntity";
	
	@Autowired
	private FinanceNoteDetailService financeNoteDetailService;
	
	@Override
    protected FinanceNoteDetailService getService () {
        return financeNoteDetailService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveFinanceNoteDetail",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveFinanceNoteDetail(@RequestBody FinanceNoteDetailEntity model) {
		
		Set<ConstraintViolation<FinanceNoteDetailEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveFinanceNoteDetail(model);
		return new DataSetObject<FinanceNoteDetailEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}