package com.wap.service;


import org.springframework.stereotype.Service;

import com.wap.entity.AliPayBack;

@Service
public class AlipayServiceImpl implements AlipayService {

	@Override
	public String payCallback(AliPayBack paramAliPayBack, String paramString1,
			String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}
	/*private static Logger logger = Logger.getLogger(AlipayServiceImpl.class);

	@Autowired
	private AgentPayOrderMapper merchantPayOrderMapper;

	@Autowired
	private AgentRewardOrderMapper agentRewardOrderMapper;

	@Autowired
	private AgentAlipayOrderMapper agentAlipayOrderMapper;

	@Autowired
	private AgentAliRewardOrderMapper agentAliRewardOrderMapper;

	@Autowired
	private MerchantOrderReturnBackCommonMapper merchantOrderReturnBackCommonMapper;

	@Autowired
	private PushService pushService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private UserService userService;
	
	@Autowired 
	AgentMerchantMapper agentMerchantMapper;
	
	@Autowired
	AgentStoreMapper agentStoreMapper;

	public String payCallback(AliPayBack aliPayBack, String distinguish, String id) {
		String result = "SUCCESS";

		logger.info("支付宝回调处理开始...");
		logger.info(new StringBuilder().append("回调处理参数..").append(aliPayBack.toString()).toString());

		Map pushFlags = new HashMap();

		String tradeNo = aliPayBack.getTrade_no();
		String outTradeNo = aliPayBack.getOut_trade_no();

		AgentPayOrderWithBLOBs agentPayOrder = null;
		AgentRewardOrder agentRewardOrder = null;
		AgentAlipayOrderCriteria agentAlipayOrderCriteria = new AgentAlipayOrderCriteria();
		AgentAliRewardOrderCriteria agentAliRewardOrderCriteria = new AgentAliRewardOrderCriteria();
		List strings;
		JSONArray jsonArr;
		if ("pay".equals(distinguish)) {
			AgentPayOrderCriteria agentPayOrderCriteria = new AgentPayOrderCriteria();
			agentPayOrderCriteria.createCriteria().andOrderNumberEqualTo(outTradeNo);
			List<AgentPayOrderWithBLOBs> agentPayOrders = this.merchantPayOrderMapper.selectByExampleWithBLOBs(agentPayOrderCriteria);
			agentPayOrder = (AgentPayOrderWithBLOBs) agentPayOrders.get(0);

			if (agentPayOrder == null) {
				result = "FAIL";
			}

			if (agentPayOrder.getStatus().equals(Dictionary.PAY_SUCCESS)) {
				return result;
			}

			if (aliPayBack.getTrade_status().equals("TRADE_SUCCESS")) {
				agentPayOrder.setStatus(Dictionary.PAY_SUCCESS);
				agentPayOrder.setOrderBody(aliPayBack.getSubject());
				agentPayOrder.setUpdateTime(new Date());
				agentPayOrder.setPayTime(new Date());

				if ((aliPayBack.getBuyer_pay_amount() != null) && (!aliPayBack.getBuyer_pay_amount().isEmpty())) {
					agentPayOrder.setRealPayAmount(new BigDecimal(aliPayBack.getBuyer_pay_amount()));
				}
				if ((aliPayBack.getReceipt_amount() != null) && (!aliPayBack.getReceipt_amount().isEmpty())) {
					agentPayOrder.setPaidInAmount(new BigDecimal(aliPayBack.getReceipt_amount()));
				}

				agentPayOrder.setDiscountAmount(agentPayOrder.getOrderAmount().subtract(agentPayOrder.getRealPayAmount()));

				this.merchantPayOrderMapper.updateByPrimaryKeySelective(agentPayOrder);

				agentAlipayOrderCriteria.createCriteria().andOrderIdEqualTo(agentPayOrder.getId());

				List<AgentAlipayOrderWithBLOBs> agentAlipayOrders = this.agentAlipayOrderMapper.selectByExampleWithBLOBs(agentAlipayOrderCriteria);
				if (agentAlipayOrders.size() == 1) {
					AgentAlipayOrderWithBLOBs agentAlipayOrderWithBLOBs = (AgentAlipayOrderWithBLOBs) agentAlipayOrders.get(0);

					strings = aliPayBack.getFund_bill_list();
					jsonArr = JSONArray.fromObject(strings);
					logger.info(new StringBuilder().append("回调参数JSON处理..").append(jsonArr.toString()).toString());

					agentAlipayOrderWithBLOBs.setTradeNo(tradeNo);
					agentAlipayOrderWithBLOBs.setBuyerLogonId(aliPayBack.getBuyer_logon_id());
					agentAlipayOrderWithBLOBs.setTotalAmount(new BigDecimal(aliPayBack.getTotal_amount()));
					agentAlipayOrderWithBLOBs.setReceiptAmount(new BigDecimal(aliPayBack.getTotal_amount()));
					agentAlipayOrderWithBLOBs.setBuyerPayAmount(new BigDecimal(aliPayBack.getTotal_amount()));
					agentAlipayOrderWithBLOBs.setPointAmount(new BigDecimal(aliPayBack.getPoint_amount()));

					agentAlipayOrderWithBLOBs.setFundBillList(jsonArr.toString());
					agentAlipayOrderWithBLOBs.setUpdateTime(new Date());
					this.agentAlipayOrderMapper.updateByPrimaryKeySelective(agentAlipayOrderWithBLOBs);
					try {
						//沙岩增加，通过推送消息返回订单信息
						Long storeId = agentPayOrder.getStoreId();
						AgentStore store;
						String storeName=null;
						
						if(storeId != null)
						{
							store = this.agentStoreMapper.selectByPrimaryKey(storeId);
							storeName = store.getStoreName();
						}
						
						this.pushService.getQrcodePayPushCidNew(pushFlags, agentPayOrder.getStoreId(),
								agentPayOrder.getMerchantId());
						this.pushService.pushByCidNew(agentPayOrder, pushFlags,agentPayOrder.getPayTime(), agentPayOrder.getOrderAmount().toString(), "1", storeName, 1);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					agentPayOrder.setStatus(Byte.valueOf(Dictionary.getALiPayStatus(aliPayBack.getTrade_status())));
					agentPayOrder.setOrderBody(aliPayBack.getSubject());
					agentPayOrder.setUpdateTime(new Date());
					agentPayOrder.setPayTime(new Date());
					this.merchantPayOrderMapper.updateByPrimaryKeySelective(agentPayOrder);
					result = "FAIL";
				}
				if ((id != null) && (!"".equals(id)))
					try {
						MerchantUserCommon myInfo = this.userService.getMyInfo(agentPayOrder.getMerchantUserId());
						MerchantPayOrderCommon orderCommon = new MerchantPayOrderCommon();
						BeanUtils.copyProperties(agentPayOrder, orderCommon);
						orderCommon.setTransactionId(aliPayBack.getTerminal_id());
						orderCommon.setRealname(myInfo.getName());
						orderCommon.setStoreName(myInfo.getStoreName());
						orderCommon.setMerchantName(myInfo.getMerchantName());
						Map map = new HashMap();
						Map map2 = new HashMap();
						map2.put("data", orderCommon);
						map2.put("success", "true");
						map.put("data", map2);
						map.put("id", id);

						String resultMsg = JSON.toJSONString(map);

						this.redisTemplate.convertAndSend("java", resultMsg);
					} catch (Exception e) {
						logger.error(e);
					}
			}
		} else if ("reward".equals(distinguish)) {				//打赏
			AgentRewardOrderCriteria agentRewardOrderCriteria = new AgentRewardOrderCriteria();
			agentRewardOrderCriteria.createCriteria().andOrderNumberEqualTo(outTradeNo);
			List agentRewardOrders = this.agentRewardOrderMapper.selectByExample(agentRewardOrderCriteria);
			agentRewardOrder = (AgentRewardOrder) agentRewardOrders.get(0);
			
			

			if (agentRewardOrder.getStatus().equals(Dictionary.PAY_SUCCESS)) {
				return result;
			}

			if (aliPayBack.getTrade_status().equals("TRADE_SUCCESS")) {
				agentRewardOrder.setStatus(Dictionary.PAY_SUCCESS);
				agentRewardOrder.setUpdateTime(new Date());
				agentRewardOrder.setPayTime(new Date());
				this.agentRewardOrderMapper.updateByPrimaryKey(agentRewardOrder);
				agentAliRewardOrderCriteria.createCriteria().andRewardOrderIdEqualTo(agentRewardOrder.getId());

				List agentAliRewardOrder = this.agentAliRewardOrderMapper
						.selectByExampleWithBLOBs(agentAliRewardOrderCriteria);
				if (agentAliRewardOrder.size() == 1) {
					AgentAliRewardOrderWithBLOBs agentAlipayOrderWithBLOBs = (AgentAliRewardOrderWithBLOBs) agentAliRewardOrder
							.get(0);

					strings = aliPayBack.getFund_bill_list();
					jsonArr = JSONArray.fromObject(strings);
					logger.info(new StringBuilder().append("回调参数JSON处理..").append(jsonArr.toString()).toString());

					agentAlipayOrderWithBLOBs.setTradeNo(tradeNo);
					agentAlipayOrderWithBLOBs.setBuyerLogonId(aliPayBack.getBuyer_logon_id());
					agentAlipayOrderWithBLOBs.setTotalAmount(new BigDecimal(aliPayBack.getTotal_amount()));
					agentAlipayOrderWithBLOBs.setReceiptAmount(new BigDecimal(aliPayBack.getTotal_amount()));
					agentAlipayOrderWithBLOBs.setBuyerPayAmount(new BigDecimal(aliPayBack.getTotal_amount()));
					agentAlipayOrderWithBLOBs.setPointAmount(new BigDecimal(aliPayBack.getPoint_amount()));

					agentAlipayOrderWithBLOBs.setFundBillList(jsonArr.toString());
					agentAlipayOrderWithBLOBs.setUpdateTime(new Date());

					this.agentAliRewardOrderMapper.updateByPrimaryKey(agentAlipayOrderWithBLOBs);
					try {
						//打赏走以前的推送消息吧
						this.pushService.getQrcodePayPushCid(pushFlags, agentRewardOrder.getStoreId(),
								agentRewardOrder.getMerchantId());
						this.pushService.pushByCid(agentPayOrder.getOrderNumber(), pushFlags,
								agentRewardOrder.getPayTime(), agentRewardOrder.getOrderAmount().toString(), "1");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					agentRewardOrder.setStatus(Byte.valueOf(Dictionary.getALiPayStatus(aliPayBack.getTrade_status())));
					agentRewardOrder.setUpdateTime(new Date());
					agentRewardOrder.setPayTime(new Date());
					this.agentRewardOrderMapper.updateByPrimaryKey(agentRewardOrder);
					result = "FAIL";
				}

			}

		}

		AgentOrderReturnBack orderReturnBack = this.merchantOrderReturnBackCommonMapper
				.getOrderReturnBackByOrderId(agentPayOrder.getId());

		if (agentPayOrder.getChannel().equals(Byte.valueOf((byte) 4))) {
			StringBuilder sb = new StringBuilder("code=0");
			sb.append("&out_trade_no=").append(agentPayOrder.getOutTradeNo());
			sb.append("&trade_id=").append(agentPayOrder.getOrderNumber());
			sb.append("&attach=").append(agentPayOrder.getAttach());

			String status2 = agentPayOrder.getStatus().toString();
			byte jsonArr1 = -1;
			switch (status2.hashCode()) {
			case 48:
				if (status2.equals("0")) {
					jsonArr1 = 0;
				}
				break;
			case 49:
				if (status2.equals("1")) {
					jsonArr1 = 1;
				}
				break;
			case 50:
				if (status2.equals("2")) {
					jsonArr1 = 5;
				}
				break;
			case 51:
				if (status2.equals("3")) {
					jsonArr1 = 4;
				}
				break;
			case 52:
				if (status2.equals("4")) {
					jsonArr1 = 2;
				}
			case 53:
			default:
				break;
			case 54:
				if (status2.equals("6")) {
					jsonArr1 = 3;
				}
			}

			switch (jsonArr1) {
			case 0:
				sb.append("&trade_state=0");
				break;
			case 1:
				sb.append("&trade_state=1");
				String payTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
				sb.append("&pay_time=").append(payTime);
				break;
			case 2:
				sb.append("&trade_state=2");
				sb.append("&err_msg=").append("系统异常");
				break;
			case 3:
				sb.append("&trade_state=3");
				break;
			case 4:
				sb.append("&trade_state=3");
				break;
			case 5:
				sb.append("&trade_state=4");
			}

			try {
				logger.info(new StringBuilder().append("returnURL=").append(orderReturnBack.getReturnUrl())
						.append("  param=").append(sb.toString()).toString());
				HttpService.sendPost(orderReturnBack.getReturnUrl(), sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;
	}*/
}