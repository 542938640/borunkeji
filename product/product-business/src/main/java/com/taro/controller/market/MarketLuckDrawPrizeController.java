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
import com.taro.entity.market.MarketLuckDrawPrizeEntity;
import com.taro.service.market.MarketLuckDrawPrizeService;

/**
 *<p>Title:MarketLuckDrawPrizeController.java</p>
 *<p>Description:幸运抽奖奖品 Action</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2020-10-27 00:21:07
 */
@Api(tags = "幸运抽奖奖品")
@RestController
@RequestMapping(value="/marketLuckDrawPrize")
public class MarketLuckDrawPrizeController extends AbstractController<MarketLuckDrawPrizeEntity> {

	public final static String MODULE = "幸运抽奖奖品";

	public final static String ENTITY = "MarketLuckDrawPrizeEntity";
	
	@Autowired
	private MarketLuckDrawPrizeService marketLuckDrawPrizeService;
	
	@Override
    protected MarketLuckDrawPrizeService getService () {
        return marketLuckDrawPrizeService;
    }
    
	@Override
    public String getModule() {
		return MODULE;
	}
	
	@ApiOperation(value = "保存" + MODULE, notes = "保存" + MODULE)
	@ApiImplicitParams({
		@ApiImplicitParam (name = "model", value = "保存实体", required = true, dataType = ENTITY, paramType = "query")
	})
	@RequestMapping(value = "/saveMarketLuckDrawPrize",consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)
	public String saveMarketLuckDrawPrize(@RequestBody MarketLuckDrawPrizeEntity model) {
		
		Set<ConstraintViolation<MarketLuckDrawPrizeEntity>> constraintViolations = validator.validate(model);
		if (!constraintViolations.isEmpty()) {
			throw new BusinessException(constraintViolations.iterator().next().getMessage());
		}
		createBefore(model);
		getService().saveMarketLuckDrawPrize(model);
		return new DataSetObject<MarketLuckDrawPrizeEntity>(model).toJson(ignoreAttr.get(Constant.IGNORE_ATTR_CREATE));
	}
	
}