package com.wap.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wap.service.OrderMessageServer;

@Controller
@RequestMapping("/orderMessage")
public class OrderMessageCon {

	@Autowired
	private OrderMessageServer messageSer;

	@RequestMapping(value = "/QueryMessage", method = RequestMethod.POST)
	public @ResponseBody
	String allMessage(String storeid) {
		if (storeid == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			long io = Long.parseLong(storeid);
			JSONObject json = messageSer.backOrderMessge(io);
			return json.toString();
		}
	}
//	storeid: storeidmerchantid: merchantid,info: info,msgtype: msgtype
	@RequestMapping(value = "/pushMessage", method = RequestMethod.POST)
	public @ResponseBody
	String pushMessage(String info, String merchantid,String storeid,String msgtype) {
		System.err.println(info+"  merchantid:"+merchantid+"storeid:"+storeid+"msgtype:"+msgtype);
		if (info == null || merchantid == null||msgtype==null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			long io = Long.parseLong(merchantid);
			long lid= Long.parseLong(storeid);
			int type= Integer.parseInt(msgtype);
			String msg = messageSer.insertMessage(type, info, io, io);
			return msg;
		}
	}

	@RequestMapping(value = "/changeOper", method = RequestMethod.POST)
	public @ResponseBody
	String updateMessage(String mid, String oid) {
		String backmsg = "";
		if (oid == null || mid == null) {
			backmsg = "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else {
			long io = Long.parseLong(mid);
			long perid = Long.parseLong(oid);
			backmsg = messageSer.changeStatus(perid, io);
		}
		return backmsg;
	}
//
//	@RequestMapping(value = "/getDevice", method = RequestMethod.GET)
//	public @ResponseBody
//	String getDevice(String mid) {
//		String backmsg = "";
//		if (mid == null) {
//			backmsg = "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
//		} else {
//			long io = Long.parseLong(mid);
//			messageSer.getDeviceToken(io);
//		}
//		return backmsg;
//	}
}
