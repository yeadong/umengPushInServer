package com.wap.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wap.service.OrderCompileSer;

@Controller
@RequestMapping("/ComOrderQuery")
public class OrderComCon {
	private static Logger logger = Logger.getLogger(OrderComCon.class);

	@Autowired
	private OrderCompileSer orderSer;

	/**
	 * 查询点餐订单
	 * 
	 * @param id
	 *            storeid
	 * @param nowpage
	 *            当前页面
	 * @param pagecon
	 *            条数
	 * @param 0 全部 1 待接单 2-已下单3 -已上齐4-已付款 5退单
	 * @return
	 */
	@RequestMapping(value = "/QueryAllCompile", method = RequestMethod.POST)
	public @ResponseBody
	String AllQuery(String id, String nowpage, String pagecon,
			String orderstatus,String orderstime,String orderetime) {
		System.err.println("orderstatus----"+orderstime+"orderetime---"+orderetime);
		String string = "";
		if (id == null||nowpage==null||pagecon==null) {
			string = "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			long io = Long.parseLong(id);
			try {
				int s = Integer.parseInt(nowpage);
				int w = Integer.parseInt(pagecon);
				int status = Integer.parseInt(orderstatus);
				if(status>2)
					status+=1;
				string = orderSer.getAllOrderCompile(io, s, w, status,orderstime,orderetime);
			} catch (Exception e) {
				e.printStackTrace();
				string = "{\"back_code\":501,\"error_msg\":\"转化异常\"}";
			}
		}
		return string;
	}

	/**
	 * 查询指定顾客的订单
	 * 
	 * @param customerId
	 * @param nowpage
	 * @return
	 */
	@RequestMapping(value = "/webQueryAllOrder", method = RequestMethod.POST)
	public @ResponseBody
	String webGetAllOrder(String customerId, String nowpage) {
		if (customerId == null || nowpage == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			int page = 0;
			try {
				page = Integer.parseInt(nowpage);
			} catch (NumberFormatException e) {
				return "{\"back_code\":407,\"error_msg\":\"转化异常\"}";
			}
			JSONObject jsonObject = orderSer.getCustomerOrder(customerId, page);
			return jsonObject.toString();
		}
	}

	@RequestMapping(value = "/QueryServing", method = RequestMethod.POST)
	public @ResponseBody
	String orderServing(String id, String begintime, String endtime) {
		String string = "";
		if (id == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		long io = Long.parseLong(id);
		string = orderSer.testAm(io, begintime, endtime);
		// string = orderSer.showAM(io, begintime, endtime);
		return string;
	}

	// 上完菜品 0 菜品上齐，1退单
	@RequestMapping(value = "/changeOrderstatus", method = RequestMethod.POST)
	public @ResponseBody
	String changeOrderstatus(String id, String deskid, String scode) {
		String string = "";
		if (id == null || deskid == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		long io = Long.parseLong(id);
		long ld = Long.parseLong(deskid);
		int status = Integer.parseInt(scode);
		string = orderSer.changeOrderinfo(io, ld, status);
		return string;
	}

	/**
	 * 更新付款信息
	 */
	@RequestMapping(value = "/changeOrderPay", method = RequestMethod.POST)
	public @ResponseBody
	String updateOrderPay(String oid, String way, String pid,String did) {
		System.err.println(oid+"   "+way+"     "+did);
		String string = "";
		if (oid == null || way == null || pid == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		long io;
		long ld;
		long dd;
		int status;
		try {
			io = Long.parseLong(oid);
			ld = Long.parseLong(pid);
			dd = Long.parseLong(did);
			status = Integer.parseInt(way);
		} catch (NumberFormatException e) {
			return "{\"back_code\":501,\"error_msg\":\"转换异常\"}";
		}
		string = orderSer.changeOrderPay(io, status, ld,dd);
		return string;

	}

}
