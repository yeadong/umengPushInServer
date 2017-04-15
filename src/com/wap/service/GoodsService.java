package com.wap.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wap.dao.GoodsMapper;
import com.wap.dao.KxfOrderDetailMapper;
import com.wap.entity.GoodsEntity;
import com.wap.entity.GoodsType;
import com.wap.entity.OrderOrderInfo;

@EnableTransactionManagement
@Service
public class GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Resource
	private KxfOrderDetailMapper detailMapper;

	@Transactional
	public JSONObject getAllGoods(long merchantid, long storeid, long ldid) {
		JSONObject backJson = new JSONObject();
		ArrayList<GoodsType> typeList = goodsMapper.selectGoodType(merchantid);

		if (typeList.size() > 0) {
			try {
				ArrayList<GoodsEntity> goodsEntities = goodsMapper
						.SelectGoodAll(merchantid);
				JSONArray typejsonarray = new JSONArray();
				JSONArray jsonArray = null;
				JSONObject eachjson;
				for (GoodsType goodsType : typeList) {
					long typeid = goodsType.getId();
					jsonArray = new JSONArray();
					for (GoodsEntity goodsEntity : goodsEntities) {
						long eid = goodsEntity.getTypeid();
						if (eid == typeid) {
							eachjson = new JSONObject(goodsEntity);
							jsonArray.put(eachjson);
						}

					}
					JSONObject typejson = new JSONObject(goodsType);
					typejson.put("arraylist", jsonArray);
					typejsonarray.put(typejson);
				}
				backJson.put("typeslist", typejsonarray);
				backJson.put("typescount", typeList.size());
				// backJson.put("goodslist", jsonArray);
				// backJson.put("listcount", goodsEntities.size());
				backJson.put("back_code", 200);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			try {
				backJson.put("back_code", 501);
				backJson.put("error_msg", "获取失败，没有数据");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return backJson;

	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// merchantId, storeId, deskId, customerId,
	// personNum, createTime, orderWay, orderAmount, orderStatus,
	// payWay, remark)
	@Transactional
	public String addOrder(long operId,long merchantId, long storeId, long deskId,
			long customerId, int personNum, String createTime, int orderWay,
			int orderAmount, int orderStatus, int payWay, long payOrderId,
			String remark, ArrayList<Map<String, Object>> aMaps) {
		String backmsg;
		Date cretime = new Date();
		try {
			cretime = dateFormat.parse(createTime);
		} catch (ParseException e) {
		}
		int am = (int) (orderAmount * 100);
		OrderOrderInfo orderOrderInfo = new OrderOrderInfo();
		orderOrderInfo.setCreateTime(cretime);
		orderOrderInfo.setCustomerId(customerId);
		orderOrderInfo.setDeskId(deskId);
		orderOrderInfo.setMerchantId(merchantId);
		orderOrderInfo.setOrderAmount(am);
		orderOrderInfo.setOrderStatus(orderStatus);
		orderOrderInfo.setOrderWay(orderWay);
		orderOrderInfo.setPayOrderId(payOrderId);
		orderOrderInfo.setPayWay(payWay);
		orderOrderInfo.setPersonNum(personNum);
		orderOrderInfo.setRemark(remark);
		orderOrderInfo.setStoreId(storeId);
		orderOrderInfo.setOperId(operId);
		int ss = goodsMapper.addOrderinfo(orderOrderInfo);

		if (ss == 1) {
			int updatedesk = goodsMapper.updateDeskStatus(deskId);
			long oid = orderOrderInfo.getId();
			for (int i = 0; i < aMaps.size(); i++) {
				try {
					Map<String, Object> map = aMaps.get(i);
					int count = (int) map.get("count");
					long id = (long) map.get("id");
					int code = goodsMapper.addOrderDetail(oid, id, count,
							cretime);
					System.err.println(code + "-----");
				} catch (RuntimeException exception) {
					backmsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";
					break;
				}
				// if(i==2){
				// throw new RuntimeException("sds");
				// }
			}
			backmsg = "{\"back_code\":200,\"orderid\":" + oid + "}";
		} else {
			backmsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";
		}

		return backmsg;
	}

	/**
	 * 加菜
	 * 
	 * @param oid
	 * @param aMaps
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String addGood(Long oid, ArrayList<Map<String, Object>> aMaps) {
		String backmsg;

		Date cretime = new Date();

		for (int i = 0; i < aMaps.size(); i++) {
			try {
				Map<String, Object> map = aMaps.get(i);
				int count = (int) map.get("count");
				long id = (long) map.get("id");
				goodsMapper.addOrderDetail(oid, id, count, cretime);

			} catch (RuntimeException exception) {
				backmsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("poid", oid);
		map.put("back_count", -1);
		detailMapper.orderinfoCcgc(map);
		int code = (int) map.get("back_count");
		backmsg = "{\"back_code\":200,\"orderid\":" + oid + "}";
		return backmsg;
	}

	public String getCat(long merchantid) {
		List<GoodsType> goodstype = detailMapper.getCatMap(merchantid);
		JSONObject jsonObject = new JSONObject();
		if (goodstype != null) {
			JSONArray jsonArray = new JSONArray();
			int size = goodstype.size();
			for (int i = 0; i < size; i++) {
				GoodsType goodsType2 = goodstype.get(i);
				JSONObject json = new JSONObject(goodsType2);
				jsonArray.put(json);
			}
			try {
				jsonObject.put("array", jsonArray);
				jsonObject.put("back_code", 200);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else {
			try {
				jsonObject.put("back_code", 501);
				jsonObject.put("error_msg", "获取失败，没有数据");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();

	}
}
