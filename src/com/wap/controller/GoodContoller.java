package com.wap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wap.service.GoodsService;

@Controller
@RequestMapping("/GoodsQuery")
public class GoodContoller {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/QueryAllGoods", method = RequestMethod.POST)
	public @ResponseBody
	String GetALLGOODS(String merchantid, String storeid, String deskid) {
		if (merchantid == null || storeid == null || deskid == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else if (merchantid.equals("")) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		long lmid = Long.parseLong(merchantid);
		long lsid = Long.parseLong(storeid);
		long ldid = Long.parseLong(deskid);
		System.out.println(lmid + lsid + ldid);
		JSONObject str = goodsService.getAllGoods(lmid, lsid, ldid);
		return str.toString();
	}

	@RequestMapping(value = "/enterWeb", method = RequestMethod.GET)
	public String enterWeb() {
		return "home";
	}

	@RequestMapping(value = "/getCat", method = RequestMethod.GET)
	public @ResponseBody
	String getAllCat(String storeid, HttpServletResponse response) {
		String back = "";
		if (storeid == null) {
			back = "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			long merchantid = Long.parseLong(storeid);
			back = goodsService.getCat(merchantid);
		}
		return back;

	};

	/**
	 * web 加菜
	 * 
	 * @param jsono
	 * @return
	 */
	@RequestMapping(value = "/addWebFood", method = RequestMethod.POST)
	public @ResponseBody
	String addWapFood(@RequestBody String jsono) {
		String back = "";
		jsono = jsono.replace("\\", "");
		jsono = jsono.replace("\"[", "[");
		jsono = jsono.replace("]\"", "]");
		try {
			JSONObject json = new JSONObject(jsono);
			String sid = json.getString("orderId");
			JSONArray array = json.getJSONArray("goodlist");
			ArrayList<Map<String, Object>> aMaps = new ArrayList<>();
			for (int i = 0; i < array.length(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject ecahobj = array.getJSONObject(i);
				int num = ecahobj.getInt("num");
				String id = ecahobj.getString("id");
				long longid = Long.parseLong(id);
				map.put("count", num);
				map.put("id", longid);
				aMaps.add(map);
			}
			System.err.println(json);
			 Long orderid=Long.parseLong(sid);
			 back = goodsService.addGood(orderid, aMaps);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return back;

	}

	// web 加单
	@RequestMapping(value = "/addWapOrder", method = RequestMethod.POST)
	public @ResponseBody
	String addWapOrder(@RequestBody String jsono) {
		String back = "";
		jsono = jsono.replace("\\", "");
		jsono = jsono.replace("\"[", "[");
		jsono = jsono.replace("]\"", "]");
		try {
			JSONObject json = new JSONObject(jsono);
			long deskid = json.getLong("deskid");
			long storeid = json.getLong("storeid");
			long customerId = json.getLong("customerId");
			int personNum = json.getInt("personNum");
			String creString = json.getString("cretime");
			String am = json.getString("orderAmount");
			int orderAmount = Integer.parseInt(am);
			String remark = json.getString("remark");
			int orderway = json.getInt("orderway");
			long merchantId = json.getLong("merchantId");
			JSONArray array = json.getJSONArray("goodlist");
			ArrayList<Map<String, Object>> aMaps = new ArrayList<>();
			for (int i = 0; i < array.length(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject ecahobj = array.getJSONObject(i);
				int num = ecahobj.getInt("num");
				String id = ecahobj.getString("id");
				long longid = Long.parseLong(id);
				map.put("count", num);
				map.put("id", longid);
				aMaps.add(map);
			}
			int orderStatus = 2, payWay = 0, payOrderId = 0;
			back = goodsService.addOrder(0l, merchantId, storeid, deskid,
					customerId, personNum, creString, orderway, orderAmount,
					orderStatus, payWay, payOrderId, remark, aMaps);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return back;
	}

	// app 加单
	@RequestMapping(value = "/AddOrders", method = RequestMethod.POST)
	public @ResponseBody
	String insertOrderinfo(@RequestBody String json) {
		String back = "";
		try {
			JSONObject jsonObject = new JSONObject(json);
			long merchantId = jsonObject.getLong("merchantId");
			long storeId = jsonObject.getLong("storeId");
			long deskId = jsonObject.getLong("deskId");
			long customerId = jsonObject.getLong("customerId");
			int personNum = jsonObject.getInt("personNum");
			int orderStatus = jsonObject.getInt("orderStatus");
			int payWay = jsonObject.getInt("payWay");
			long payOrderId = jsonObject.getLong("payOrderId");
			long operid = jsonObject.getLong("operId");
			String creString = jsonObject.getString("createTime");

			// long createTime = jsonObject.get("createTime").toString();
			int orderWay = jsonObject.getInt("orderWay");
			int orderAmount = jsonObject.getInt("orderAmount");
			String remark = jsonObject.getString("remark");
			JSONArray details = jsonObject.getJSONArray("details");
			ArrayList<Map<String, Object>> aMaps = new ArrayList<>();
			for (int i = 0; i < details.length(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject detail = details.getJSONObject(i);
				int count = detail.getInt("number");
				// String name = detail.getString("goods");
				long id = detail.getInt("id");
				map.put("count", count);
				// map.put("name", name);
				map.put("id", id);
				aMaps.add(map);
			}

			back = goodsService.addOrder(operid, merchantId, storeId, deskId,
					customerId, personNum, creString, orderWay, orderAmount,
					orderStatus, payWay, payOrderId, remark, aMaps);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(back);
		return back;
	}

	// app 加菜

	@RequestMapping(value = "/addGoods", method = RequestMethod.POST)
	public @ResponseBody
	String addGoodsByOrderid(@RequestBody String params) {
		if (params == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		String back = "";
		JSONObject json;
		ArrayList<Map<String, Object>> aMaps = null;
		Long oid = null;
		try {
			json = new JSONObject(params);
			String orderid = json.getString("orderid");
			JSONArray arrayjson = json.getJSONArray("goods");
			aMaps = new ArrayList<>();
			oid = Long.parseLong(orderid);
			for (int i = 0; i < arrayjson.length(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject detail = arrayjson.getJSONObject(i);
				int count = detail.getInt("number");
				long id = detail.getInt("id");
				map.put("count", count);
				map.put("id", id);
				aMaps.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		back = goodsService.addGood(oid, aMaps);
		return back;
	}

}
