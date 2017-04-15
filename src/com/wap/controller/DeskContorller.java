package com.wap.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wap.service.DeskServer;

@Controller
@RequestMapping("/DeskQuery")
public class DeskContorller {
	@Autowired
	private DeskServer deskServer;

	@RequestMapping(value = "/QueryDesk", method = RequestMethod.POST)
	public @ResponseBody
	String GetALLGOODS(String id, String status) {
		if (id == null || status == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		} else if (id.equals("")) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		int isatus = Integer.parseInt(status);
		long io = Long.parseLong(id);
		JSONObject str = deskServer.getDeskByStaus(io, isatus);
		return str.toString();
	}

	@RequestMapping(value = "/CheckOrderId", method = RequestMethod.POST)
	public @ResponseBody
	String checkOrderid(String deskid) {
		if (deskid == null) {
			return "{\"back_code\":301,\"error_msg\":\"不能为空\"}";
		}
		long io = Long.parseLong(deskid);
		String backmsg = deskServer.getOrderId(io);
		return backmsg;
	}

}
