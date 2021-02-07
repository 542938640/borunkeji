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
import com.taro.entity.market.MarketPayActPrizeEntity;
import com.taro.service.market.MarketPayActPrizeService;

/**
 *<p>Title:MarketPayActPrizeController.java</p>
 *<p>Description:支付活动奖品 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:48
 */
@Api(tags = "支付活动奖品")
@RestController
@RequestMapping(value="/marketPayActPrize")
public class MarketPayActPrizeController extends AbstractController<MarketPayActPrizeEntity> {

	public final static String MODULE = "支付活动奖品";

	public final static String ENTITY = "MarketPayActPrizeEntity";
	
	@Autowired
	private MarketPayActPrizeService marketPayActPrizeService;
	
	@Override
    protected MarketPayActPrizeService getService () {
        return marketPayActPrizeService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketPayActPrize",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketPayActPrize(@RequestBody MarketPayActPrizeEntity model) {
		
		Set<ConstraintViolation<MarketPayActPrizeEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveMarketPayActPrize(model);
		return new DataSetObject<MarketPayActPrizeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}