package com.taro.controller.market;import java.util.List;import java.util.Map;import javax.servlet.http.HttpServletRequest;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import org.springframework.web.multipart.MultipartFile;import com.taro.common.DataSet;import com.taro.common.DataSetObject;import com.taro.constants.Constant;import com.taro.entity.market.MarketGiftEntity;import com.taro.entity.market.MarketLuckDrawEntity;import com.taro.entity.market.MarketPayActEntity;import com.taro.entity.market.OrderExtEntity;import com.taro.service.market.MarketAppService;import io.swagger.annotations.ApiImplicitParam;import io.swagger.annotations.ApiImplicitParams;import io.swagger.annotations.ApiOperation;@RestController@RequestMapping(value="/app/market")public class MarketAppController {	@Autowired	private MarketAppService marketAppService;		@ApiOperation(value = "获取积分换礼列表数据", notes = "获取积分换礼列表数据")	@ApiImplicitParams({		@ApiImplicitParam (name = "param", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")	})	@RequestMapping(value = "/gift/list",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)	public String listGift(HttpServletRequest request, @RequestParam Map<String, Object> param){		List<MarketGiftEntity> result = marketAppService.listGift(param);		return new DataSet<MarketGiftEntity>(result).toJson();	}	@ApiOperation(value = "积分换礼", notes = "积分换礼")	@ApiImplicitParams({		@ApiImplicitParam (name = "model", value = "新增实体", required = true, dataType = "T", paramType = "query")	})    @RequestMapping(value="/gift/save",method=RequestMethod.POST,produces ="application/json; charset=UTF-8")//	@RequestMapping(value = "/gift/save",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)	public String saveGift(@RequestParam("uploadfile") MultipartFile[] files, OrderExtEntity model) {		OrderExtEntity saveModel = marketAppService.saveGift(model, files);		return new DataSetObject<OrderExtEntity>(saveModel).toJson();	}	@ApiOperation(value = "根据设备did获取幸运抽奖在运行活动详情", notes = "根据设备did获取幸运抽奖在运行活动详情")	@ApiImplicitParams({		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")	})	@RequestMapping(value = "/luckdraw/getRunByDevice/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)	public String getLuckdrawRunByDevice(@PathVariable("id") String id) {		MarketLuckDrawEntity model = marketAppService.getLuckdrawRunByDevice(id);		return new DataSetObject<MarketLuckDrawEntity>(model).toJson();	}	@ApiOperation(value = "幸运抽奖", notes = "幸运抽奖")	@ApiImplicitParams({		@ApiImplicitParam (name = "model", value = "新增实体", required = true, dataType = "T", paramType = "query")	})    @RequestMapping(value="/luckdraw/save",method=RequestMethod.POST,produces ="application/json; charset=UTF-8")	public String saveLuckdraw(@RequestParam("uploadfile") MultipartFile[] files, OrderExtEntity model) {		OrderExtEntity saveModel = marketAppService.saveLuckdraw(model, files);		return new DataSetObject<OrderExtEntity>(saveModel).toJson();	}		@ApiOperation(value = "根据设备did获取支付活动在运行活动详情", notes = "根据设备did获取支付活动在运行活动详情")	@ApiImplicitParams({		@ApiImplicitParam (name = "id", value = "实例id", required = true, dataType = "String", paramType = "query")	})	@RequestMapping(value = "/payact/getRunByDevice/{type}/{id}", produces = Constant.JSON_UTF_8,method = RequestMethod.GET)	public String getPayactRunByDevice(@PathVariable("type") String type, @PathVariable("id") String id) {		MarketPayActEntity model = marketAppService.getPayactRunByDevice(type, id);		return new DataSetObject<MarketPayActEntity>(model).toJson();	}	@ApiOperation(value = "支付活动签名", notes = "支付活动签名")	@ApiImplicitParams({		@ApiImplicitParam (name = "model", value = "新增实体", required = true, dataType = "T", paramType = "query")	})    @RequestMapping(value="/payact/saveSign",method=RequestMethod.POST,produces ="application/json; charset=UTF-8")	public String savePayactSign(@RequestParam("uploadfile") MultipartFile[] files, OrderExtEntity model) {		OrderExtEntity saveModel = marketAppService.savePayactSign(model, files);		return new DataSetObject<OrderExtEntity>(saveModel).toJson();	}	@ApiOperation(value = "支付活动回调", notes = "支付活动回调")	@ApiImplicitParams({		@ApiImplicitParam (name = "model", value = "新增实体", required = true, dataType = "T", paramType = "query")	})	@RequestMapping(value = "/payact/saveCallback",produces = Constant.JSON_UTF_8,consumes = Constant.JSON_UTF_8,method = RequestMethod.POST)	public String savePayactCallback(@RequestBody OrderExtEntity model) {		marketAppService.savePayactCallback(model);		return new DataSetObject<OrderExtEntity>(model).toJson();	}	@ApiOperation(value = "获取APP首页活动数据", notes = "获取APP首页活动数据")	@ApiImplicitParams({		@ApiImplicitParam (name = "param", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")	})	@RequestMapping(value = "/listAppHomeNum",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)	public String listAppHomeNum(HttpServletRequest request, @RequestParam Map<String, Object> param){		List<OrderExtEntity> result = marketAppService.listAppHomeNum(param);		return new DataSet<OrderExtEntity>(result).toJson();	}	@ApiOperation(value = "获取APP首页活动每日数据", notes = "获取APP首页活动每日数据")	@ApiImplicitParams({		@ApiImplicitParam (name = "param", value = "查询条件参数", required = true, dataType = "Map<String, Object>", paramType = "query")	})	@RequestMapping(value = "/listAppHomeDaysNum",produces = Constant.JSON_UTF_8,method = RequestMethod.GET)	public String listAppHomeDaysNum(HttpServletRequest request, @RequestParam Map<String, Object> param){		List<OrderExtEntity> result = marketAppService.listAppHomeDaysNum(param);		return new DataSet<OrderExtEntity>(result).toJson();	}}