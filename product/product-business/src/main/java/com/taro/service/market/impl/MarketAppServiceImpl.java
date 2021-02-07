package com.taro.service.market.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taro.dao.market.MarketGiftDao;
import com.taro.dao.market.MarketLuckDrawDao;
import com.taro.entity.device.TSlotInfoEntity;
import com.taro.entity.file.FileManageEntity;
import com.taro.entity.market.MarketGiftEntity;
import com.taro.entity.market.MarketLuckDrawEntity;
import com.taro.entity.market.MarketLuckDrawPrizeEntity;
import com.taro.entity.market.MarketPayActEntity;
import com.taro.entity.market.MarketPayActPrizeEntity;
import com.taro.entity.market.OrderExtEntity;
import com.taro.entity.market.TOrderInfoEntity;
import com.taro.exception.BusinessException;
import com.taro.service.device.TSlotInfoService;
import com.taro.service.file.FileManageService;
import com.taro.service.market.MarketAppService;
import com.taro.service.market.MarketGiftService;
import com.taro.service.market.MarketLuckDrawPrizeService;
import com.taro.service.market.MarketLuckDrawService;
import com.taro.service.market.MarketPayActPrizeService;
import com.taro.service.market.MarketPayActService;
import com.taro.service.market.OrderExtService;
import com.taro.service.market.TOrderInfoService;
import com.taro.utils.DateUtil;
import com.taro.utils.MyStringUtil;
import com.taro.utils.UuidUtil;

@Service
public class MarketAppServiceImpl implements MarketAppService {

	private static final Logger LOG = Logger.getLogger(MarketAppServiceImpl.class);
	
	@Autowired
	private OrderExtService orderExtService;
	
	@Autowired
	private TOrderInfoService tOrderInfoService;
	
	@Autowired
	private FileManageService fileManageService;
	
	@Autowired
	private MarketGiftService marketGiftService;

	@Autowired
	private MarketGiftDao marketGiftDao;

	@Autowired
	private MarketLuckDrawService marketLuckDrawService;

	@Autowired
	private MarketLuckDrawPrizeService marketLuckDrawPrizeService;

	@Autowired
	private MarketLuckDrawDao marketLuckDrawDao;
	
	@Autowired
	private MarketPayActService marketPayActService;

	@Autowired
	private MarketPayActPrizeService marketPayActPrizeService;

	@Autowired
	private TSlotInfoService tSlotInfoService;
	
	@Override
    public List<OrderExtEntity> listAppHomeNum(Map<String, Object> param){
    	return orderExtService.listAppHomeNum(param);
    }

	@Override
    public List<OrderExtEntity> listAppHomeDaysNum(Map<String, Object> param){
    	return orderExtService.listAppHomeDaysNum(param);
    }
	
	private String saveSignFile(MultipartFile[] files, String type) {
		String busi_key = UuidUtil.get32UUID();
		
		FileManageEntity fileParam = new FileManageEntity();
		fileParam.setBusi_type(type);
		fileParam.setType(type);
		fileParam.setBusi_key(busi_key);
		fileParam.setBusi_flag("1");
		fileManageService.uploadFile(files, fileParam);
		
		return busi_key;
	}
	
	@Override
    public List<MarketGiftEntity> listGift(Map<String, Object> param) {
        return marketGiftService.list(param);
    }

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
    public OrderExtEntity saveGift(OrderExtEntity model, MultipartFile[] files) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }else if(MyStringUtil.isBlank(model.getDevice_did())) {
            throw new BusinessException("设备id不能为空");
        }else if(MyStringUtil.isBlank(model.getPrize_pid())) {
            throw new BusinessException("礼品id不能为空");
        }else if(MyStringUtil.isBlank(model.getUser_phone())) {
            throw new BusinessException("用户手机不能为空");
        }else if(null == files || files.length <= 0) {
            throw new BusinessException("用户签名不能为空");
        }
        
        Integer prize_num = model.getPrize_num();
        if(prize_num <= 0) {
        	prize_num = 1;
        }
        
        String giftId = model.getPrize_pid();
        MarketGiftEntity giftModel = marketGiftDao.getAndLock(giftId);
        if (null == giftModel) {
            throw new BusinessException("礼品id查询为空");
        }
        Integer stock = giftModel.getStock();
        if(prize_num >= stock) {
            throw new BusinessException("礼品库存数不足,不能进行兑换");
        }

        // 减少库存
        MarketGiftEntity updateModel = new MarketGiftEntity();
        updateModel.setId(giftModel.getId());
        updateModel.setStock(stock - prize_num);
        marketGiftService.update(updateModel);
        
        // 增加订单
        OrderExtEntity orderExt = new OrderExtEntity();
        orderExt.setAct_type("1");
        orderExt.setDevice_did(model.getDevice_did());
        orderExt.setPrize_pid(model.getPrize_pid());
        orderExt.setUser_phone(model.getUser_phone());
        orderExt.setUser_sign(saveSignFile(files, "giftSign"));
        orderExt.setC1(model.getC1());// 卡类型
        orderExt.setC2(model.getC2());// 本网点卡号后6位
        orderExt.setC3(model.getC3());// 业务类型
        orderExt.setC4(model.getC4());
        orderExt.setC5(prize_num + "");
        orderExt.setRemark(model.getRemark());// 备注
        orderExtService.save(orderExt);
        
        return model;
    }

    @Override
    public MarketLuckDrawEntity getLuckdrawRunByDevice(String device_did) {
    	return marketLuckDrawService.getRunByDevice(device_did);
    }

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
    public OrderExtEntity saveLuckdraw(OrderExtEntity model, MultipartFile[] files) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }else if(MyStringUtil.isBlank(model.getDevice_did())) {
            throw new BusinessException("设备id不能为空");
        }else if(MyStringUtil.isBlank(model.getAct_pid())) {
            throw new BusinessException("活动id不能为空");
        }else if(MyStringUtil.isBlank(model.getUser_phone())) {
            throw new BusinessException("用户手机不能为空");
        }else if(null == files || files.length <= 0) {
            throw new BusinessException("用户签名不能为空");
        }

		MarketLuckDrawEntity marketLuckDrawModel = marketLuckDrawService.getAndLock(model.getAct_pid());
		
		if(null == marketLuckDrawModel) {
            throw new BusinessException("活动不存在!");
		}else if(MyStringUtil.isBlank(marketLuckDrawModel.getStatus())
				|| !"1".equals(marketLuckDrawModel.getStatus())) {
            throw new BusinessException("活动已下线!");
		}else if(CollectionUtils.isEmpty(marketLuckDrawModel.getPrizeList())) {
            throw new BusinessException("活动奖品为空!");
		}

		// 是否达到限制人数
		if(MyStringUtil.isNotBlank(marketLuckDrawModel.getIs_number())
				&& "1".equals(marketLuckDrawModel.getIs_number())) {
			Long count = marketLuckDrawDao.listOrderCount(model.getAct_pid());
			if(count >= marketLuckDrawModel.getNumber()) {
	            throw new BusinessException("对不起,活动参与人数已满!");
			}
		}
		
		String user_phone = model.getUser_phone();

		// 是否指定用户参与
		if(MyStringUtil.isNotBlank(marketLuckDrawModel.getIs_specific_user())
				&& "1".equals(marketLuckDrawModel.getIs_specific_user())
				&& ( MyStringUtil.isBlank(marketLuckDrawModel.getSpecific_user())
						|| !marketLuckDrawModel.getSpecific_user().contains(user_phone) )) {
            throw new BusinessException("对不起,您无法参与该活动,请确认!");
		}
		
		// 是否达到用户参与频率
		if(MyStringUtil.isNotBlank(marketLuckDrawModel.getIs_frequency())
				&& !"0".equals(marketLuckDrawModel.getIs_frequency())) {
			Map<String, Object> queryMap = Maps.newHashMap();
			if(MyStringUtil.isNotBlank(marketLuckDrawModel.getStart_time())) {
				String start_time = marketLuckDrawModel.getStart_time();
				queryMap.put("start_time", start_time.substring(0, 4) + "-" + start_time.substring(4, 6) + "-" + start_time.substring(6, 8));
			}
			if(MyStringUtil.isNotBlank(marketLuckDrawModel.getEnd_time())) {
				String end_time = marketLuckDrawModel.getEnd_time();
				queryMap.put("end_time", end_time.substring(0, 4) + "-" + end_time.substring(4, 6) + "-" + end_time.substring(6, 8));
			}
			queryMap.put("act_type", "2");
			queryMap.put("act_pid", model.getAct_pid());
			List<OrderExtEntity> queryList = orderExtService.listOrder(queryMap);
			if(CollectionUtils.isNotEmpty(queryList)) {
				if("1".equals(marketLuckDrawModel.getIs_frequency())) {// 指定活动参与周期
					if(null != marketLuckDrawModel.getFrequency_max_num()
							&& queryList.size() >= marketLuckDrawModel.getFrequency_max_num()) {// 最大参与次数
						throw new BusinessException("对不起,您已达到参与活动次数,无法再次参加!");
					}
					switch (marketLuckDrawModel.getFrequency_type()) {
					case "1": // 每天一次
						for(OrderExtEntity order : queryList) {
							if(null != order.getOrder_info_time() && DateUtil.isToday(order.getOrder_info_time().getTime())) {
								throw new BusinessException("对不起,您今天已经参与活动,无法再次参加!");
							}
						}
						break;
					case "2": // 每周一次
						for(OrderExtEntity order : queryList) {
							if(null != order.getOrder_info_time() && DateUtil.isThisWeek(order.getOrder_info_time().getTime())) {
								throw new BusinessException("对不起,您本周已经参与活动,无法再次参加!");
							}
						}
						break;
					case "3": // 每月一次
						for(OrderExtEntity order : queryList) {
							if(null != order.getOrder_info_time() && DateUtil.isThisMonth(order.getOrder_info_time().getTime())) {
								throw new BusinessException("对不起,您本月已经参与活动,无法再次参加!");
							}
						}
						break;
					default:
						break;
					}
				}else if("2".equals(marketLuckDrawModel.getIs_frequency())
						&& (null == marketLuckDrawModel.getFrequency_num()
						|| queryList.size() >= marketLuckDrawModel.getFrequency_num())) {// 指定活动参与次数
					throw new BusinessException("对不起,您已达到参与活动次数,无法再次参加!");
				}
			}
		}
		
        // 增加订单
        OrderExtEntity orderExt = new OrderExtEntity();
        orderExt.setAct_type("2");
        orderExt.setDevice_did(model.getDevice_did());
        orderExt.setAct_pid(model.getAct_pid());
        orderExt.setUser_phone(user_phone);
        orderExt.setUser_sign(saveSignFile(files, "luckDrawSign"));
        orderExt.setC1(model.getC1());// 卡类型
        orderExt.setC2(model.getC2());// 本网点卡号后6位
        orderExt.setC3(model.getC3());// 业务类型
        orderExt.setC4(model.getC4());
        orderExt.setC5(model.getC5());
        orderExt.setRemark(model.getRemark());// 备注
        
        Award award = null;
		Integer grade = 0;// 中奖等级,为0代表没中奖
		List<Award> awardList = Lists.newArrayList();
		for(MarketLuckDrawPrizeEntity prize : marketLuckDrawModel.getPrizeList()) {
			// 奖品数是否为空
			if(prize.getNum() <= 0) {
				continue;
			}
			if(MyStringUtil.isNotBlank(prize.getPhone())) {
				// 是否指定该用户中奖
				if(prize.getPhone().contains(user_phone)) {
					award = new Award(prize.getId(), 0.1f, prize.getNum(), prize.getSort());
					break;
				}
			}else {
				awardList.add(new Award(prize.getId(), 0.1f, prize.getNum(), prize.getSort()));
			}
		}
		
		if(null == award) {
			if(CollectionUtils.isEmpty(awardList)) {
	            throw new BusinessException("活动奖品已抽完,谢谢参与!");
			}
			award = lottery(awardList);
		}

		if(null != award) {
			grade = award.getGrade();
			orderExt.setPrize_pid(award.getId());
			
			MarketLuckDrawPrizeEntity updatePrizeModel = new MarketLuckDrawPrizeEntity();
			updatePrizeModel.setId(award.getId());
			updatePrizeModel.setNum(award.getCount() - 1);
			marketLuckDrawPrizeService.update(updatePrizeModel);
		}
		
        orderExt.setPrize_grade(grade);
        orderExtService.save(orderExt);
        
        return orderExt;
    }
	
	class Award {
	    /**编号*/
	    public String id;
	    /**概率（0.1代表10%，最多3位小数，即千分之一级）*/
	    public float probability;
	    /**数量（该类奖品剩余数量）*/
	    public int count;
	    /**等级（1,2,3,4,5,6）*/
	    public int grade;

	    public Award(String id, float probability, int count, int grade) {
	        super();
	        this.id = id;
	        this.probability = probability;
	        this.count = count;
	        this.grade = grade;
	    }
	    public Award(){

	    }
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public float getProbability() {
			return probability;
		}
		public void setProbability(float probability) {
			this.probability = probability;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
	}
	
	private Award lottery(List<Award> awards) {
		//总的概率区间
        float totalPro = 0f;
        //存储每个奖品新的概率区间
        List<Float> proSection = Lists.newArrayList();
        proSection.add(0f);
        //遍历每个奖品，设置概率区间，总的概率区间为每个概率区间的总和
        for (Award award : awards) {
            //每个概率区间为奖品概率乘以1000（把三位小数转换为整）再乘以剩余奖品数量
            totalPro += award.probability * 10 * award.count;
            proSection.add(totalPro);
        }
        //获取总的概率区间中的随机数
        float randomPro = (float)new Random().nextInt((int)totalPro);
        //判断取到的随机数在哪个奖品的概率区间中
        for (int i = 0,size = proSection.size(); i < size; i++) {
            if(randomPro >= proSection.get(i) 
                && randomPro < proSection.get(i + 1)){
                return awards.get(i);
            }
        }
        return null;
	}

    @Override
    public MarketPayActEntity getPayactRunByDevice(String type, String device_did) {
    	return marketPayActService.getRunByDevice(device_did, type);
    }
    
	@Override
    public OrderExtEntity savePayactSign(OrderExtEntity model, MultipartFile[] files) {
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }else if(MyStringUtil.isBlank(model.getDevice_did())) {
            throw new BusinessException("设备id不能为空");
        }else if(MyStringUtil.isBlank(model.getAct_type())) {
            throw new BusinessException("活动类型不能为空");
        }else if(MyStringUtil.isBlank(model.getAct_pid())) {
            throw new BusinessException("活动id不能为空");
        }else if(MyStringUtil.isBlank(model.getUser_phone())) {
            throw new BusinessException("用户手机不能为空");
        }else if(null == files || files.length <= 0) {
            throw new BusinessException("用户签名不能为空");
        }

		MarketPayActEntity marketPayActModel = marketPayActService.getMarketPayAct(model.getAct_pid());
		
		if(null == marketPayActModel) {
            throw new BusinessException("活动不存在!");
		}else if(MyStringUtil.isBlank(marketPayActModel.getStatus())
				|| !"1".equals(marketPayActModel.getStatus())) {
            throw new BusinessException("活动已下线!");
		}else if(CollectionUtils.isEmpty(marketPayActModel.getPrizeList())) {
            throw new BusinessException("活动奖品为空!");
		}
		
		String user_phone = model.getUser_phone();
		
		// 是否指定用户参与
		if(MyStringUtil.isNotBlank(marketPayActModel.getIs_specific_user())
				&& "1".equals(marketPayActModel.getIs_specific_user())
				&& ( MyStringUtil.isBlank(marketPayActModel.getSpecific_user())
						|| !marketPayActModel.getSpecific_user().contains(user_phone) )) {
            throw new BusinessException("对不起,您无法参与该活动,请确认!");
		}
		
		// 是否达到用户参与频率
		if(MyStringUtil.isNotBlank(marketPayActModel.getIs_frequency())
				&& !"0".equals(marketPayActModel.getIs_frequency())) {
			Map<String, Object> queryMap = Maps.newHashMap();
			if(MyStringUtil.isNotBlank(marketPayActModel.getStart_time())) {
				String start_time = marketPayActModel.getStart_time();
				queryMap.put("start_time", start_time.substring(0, 4) + "-" + start_time.substring(4, 6) + "-" + start_time.substring(6, 8));
			}
			if(MyStringUtil.isNotBlank(marketPayActModel.getEnd_time())) {
				String end_time = marketPayActModel.getEnd_time();
				queryMap.put("end_time", end_time.substring(0, 4) + "-" + end_time.substring(4, 6) + "-" + end_time.substring(6, 8));
			}
			queryMap.put("act_type", model.getAct_type());
			queryMap.put("act_pid", model.getAct_pid());
			List<OrderExtEntity> queryList = orderExtService.listOrder(queryMap);
			if(CollectionUtils.isNotEmpty(queryList)) {
				if("1".equals(marketPayActModel.getIs_frequency())) {// 指定活动参与周期
					if(null != marketPayActModel.getFrequency_max_num()
							&& queryList.size() >= marketPayActModel.getFrequency_max_num()) {// 最大参与次数
						throw new BusinessException("对不起,您已达到参与活动次数,无法再次参加!");
					}
					switch (marketPayActModel.getFrequency_type()) {
					case "1": // 每天一次
						for(OrderExtEntity order : queryList) {
							if(null != order.getOrder_info_time() && DateUtil.isToday(order.getOrder_info_time().getTime())) {
								throw new BusinessException("对不起,您今天已经参与活动,无法再次参加!");
							}
						}
						break;
					case "2": // 每周一次
						for(OrderExtEntity order : queryList) {
							if(null != order.getOrder_info_time() && DateUtil.isThisWeek(order.getOrder_info_time().getTime())) {
								throw new BusinessException("对不起,您本周已经参与活动,无法再次参加!");
							}
						}
						break;
					case "3": // 每月一次
						for(OrderExtEntity order : queryList) {
							if(null != order.getOrder_info_time() && DateUtil.isThisMonth(order.getOrder_info_time().getTime())) {
								throw new BusinessException("对不起,您本月已经参与活动,无法再次参加!");
							}
						}
						break;
					default:
						break;
					}
				}else if("2".equals(marketPayActModel.getIs_frequency())
						&& (null == marketPayActModel.getFrequency_num()
						|| queryList.size() >= marketPayActModel.getFrequency_num())) {// 指定活动参与次数
					throw new BusinessException("对不起,您已达到参与活动次数,无法再次参加!");
				}
			}
		}
		
        // 增加订单
        OrderExtEntity orderExt = new OrderExtEntity();
        orderExt.setDevice_did(model.getDevice_did());
        orderExt.setAct_type(model.getAct_type());
        orderExt.setAct_pid(model.getAct_pid());
        orderExt.setUser_phone(model.getUser_phone());
        orderExt.setUser_sign(saveSignFile(files, "payActSign_" + model.getAct_type()));
        orderExt.setC1(model.getC1());// 卡类型
        orderExt.setC2(model.getC2());// 本网点卡号后6位
        orderExt.setC3(model.getC3());// 业务类型
        orderExt.setC4(model.getC4());
        orderExt.setC5(model.getC5());
        orderExt.setRemark(model.getRemark());// 备注
        orderExtService.save(orderExt);
        
        return orderExt;
	}

	@Override
    public OrderExtEntity savePayactCallback(OrderExtEntity model) {
        LOG.info("savePayactCallback--->开始支付订单回调");
        
        if (model == null) {
            throw new BusinessException("保存对象不能为空");
        }else if(MyStringUtil.isBlank(model.getId())) {
            throw new BusinessException("id不能为空");
        }else if(MyStringUtil.isBlank(model.getOrder_info_no())) {
            throw new BusinessException("订单号不能为空");
        }else if(MyStringUtil.isBlank(model.getPrize_pid())) {
            throw new BusinessException("商品id不能为空");
        }

        // 更新订单
        OrderExtEntity orderExt = new OrderExtEntity();
        orderExt.setId(model.getId());
        orderExt.setPrize_pid(model.getPrize_pid());
        
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("ororderno", model.getOrder_info_no());
        queryMap.put("year", String.valueOf(new Date().getYear() + 1900));
        queryMap.put("month", String.valueOf(new Date().getMonth() + 1));
        List<TOrderInfoEntity> queryList = tOrderInfoService.list(queryMap);
        if(CollectionUtils.isNotEmpty(queryList)) {
            orderExt.setOrder_info(Integer.valueOf(queryList.get(0).getId()));
        }
        
        orderExtService.update(orderExt);
        
        if(StringUtils.isNotBlank(model.getPrize_pid())) {
        	String prize_pid = model.getPrize_pid();
        	MarketPayActPrizeEntity prizeModel = marketPayActPrizeService.get(prize_pid);
        	if(null != prizeModel) {
            	Integer num = Integer.valueOf(prizeModel.getC3()) - 1;
            	prizeModel.setC3(String.valueOf(num));
            	marketPayActPrizeService.update(prizeModel);
            	
            	if(StringUtils.isNotBlank(prizeModel.getSlot_pid())) {
            		TSlotInfoEntity slotModel = tSlotInfoService.get(prizeModel.getSlot_pid());
            		slotModel.setSistockquantity(slotModel.getSistockquantity() - 1);
            		tSlotInfoService.update(slotModel);
            	}
        	}
        }

        LOG.info("savePayactCallback--->结束支付订单回调");
        return model;
	}
	
}
