package com.wap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wap.service.WapOrderSer;

@Controller
@RequestMapping("/OrderQuery")
public class WapOrdering {
	@Autowired
	private WapOrderSer orderSer;

	@RequestMapping(value = "/AllQuery", method = RequestMethod.POST)
	public @ResponseBody
	String AllQuery(String id) {
		String string = "";
		long io = Long.parseLong(id);
		try {
			string = orderSer.getOrderIngs(io);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------>" + string);
		return string;
	}

	@RequestMapping(value = "/QueryByID", method = RequestMethod.POST)
	public @ResponseBody
	String QueryByID(String id) {
		String string = "";
		long io = Long.parseLong(id);
		try {
			string = orderSer.getOrderByid(io);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------>" + string);
		return string;
	}

	@RequestMapping(value = "/QueryOrderDetail", method = RequestMethod.POST)
	public @ResponseBody
	String QueryOrderDetail(String OrderId) {
		System.out.println("QueryOrderDetail---->" + OrderId);
		String string = "";
		if (OrderId != null) {
			if (!OrderId.isEmpty()) {
				long io = Long.parseLong(OrderId);
				try {
					string = orderSer.getDetailByID(io);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// System.out.println(string);
			return string;
		} else {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
	}

	@RequestMapping(value = "/updateOrderNum", method = RequestMethod.GET)
	public void updateOrderNum(int count, String id) {
		if (id != null) {
			long io = Long.parseLong(id);
			orderSer.updateNum(count, io);
		}
	}

	/**
	 * 操作员app 接单 拒单
	 * 
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/sendAll", method = RequestMethod.POST)
	public @ResponseBody
	String Orderfinsh(@RequestBody String content) {
		String backmsg = "";
		List<Map<String, Object>> numlis = new ArrayList<>();
		List<Long> dellis = new ArrayList<>();
		System.out.println("------>" + content);
		try {
			JSONObject json = new JSONObject(content);
			Long oidl = json.getLong("orderid");
			Long operid = json.getLong("operid");
			String did = json.getString("deskId");
			double amd = json.getDouble("orderam");
			int ami = (int) (amd * 100);
			orderSer.changeOrderAm(ami, oidl, operid);
			long deskid = Long.parseLong(did);
			orderSer.changeDeskUser(deskid);
			JSONArray eacharr = json.getJSONArray("content");
			JSONArray delarr = json.getJSONArray("dellist");
			if (eacharr.length() > 0) {
				Map<String, Object> map;
				for (int i = 0; i < eacharr.length(); i++) {
					map = new HashMap<>();
					JSONObject twoobj = eacharr.getJSONObject(i);
					Long llid = twoobj.getLong("id");
					int inum = twoobj.getInt("num");
					map.put("llid", llid);
					map.put("inum", inum);
					numlis.add(map);
				}
				orderSer.updateNum(numlis);
			}
			if (delarr.length() > 0) {
				for (int i = 0; i < delarr.length(); i++) {
					Long llid = delarr.getLong(i);
					dellis.add(llid);
				}
				orderSer.updateDel(dellis);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return backmsg;
	}

	@RequestMapping(value = "/updateOrderDel", method = RequestMethod.GET)
	public void updateOrderDel(String oid, String id) {
		if (id != null) {
			long io = Long.parseLong(id);
			long ioid = Long.parseLong(oid);
			orderSer.updateDel(io, ioid);
		}
	}

	@RequestMapping(value = "/changeGS", method = RequestMethod.POST)
	public @ResponseBody
	String changeGood(int isud, String id, String orderid) {
		System.err.println(isud + "    " + id + "   " + orderid);
		if (id == null || orderid == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		long pid = Long.parseLong(id);
		String s = "{\"back_code\":505,\"error_msg\":\"未同步\"}";
		if (isud == 1) {
			s = orderSer.changeGoodStatus(pid);

		} else if (isud == 2) {
			long oid = Long.parseLong(orderid);
			s = orderSer.updateDel(pid, oid);
		}
		return s;
	}

	@RequestMapping(value = "/testchange", method = RequestMethod.GET)
	public @ResponseBody
	String ChangeOrder(String id) {
		long pid = Long.parseLong(id);
		// String s = orderSer.updateDel(pid);
		return "";
		// orderSer.changeOrder(81l);
	}

	/**
	 * 更改订单付款信息
	 * 
	 * @param orderid
	 * @param operid
	 * @return
	 */
	@RequestMapping(value = "/changeOrderPay", method = RequestMethod.POST)
	public @ResponseBody
	String ChangeOrderPay(String operid, String orderid) {
		String backmsg = "";
		if (operid == null || orderid == null) {
			backmsg = "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			long pid = Long.parseLong(operid);
			long oid = Long.parseLong(orderid);
			backmsg = orderSer.orderPayStatus(oid, pid);
		}
		return backmsg;
	}

}
