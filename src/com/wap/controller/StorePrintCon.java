package com.wap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wap.service.StorePrintServer;

@Controller
@RequestMapping("/StorePrint")
public class StorePrintCon {
	@Autowired
	private StorePrintServer printServer;

	@RequestMapping(value = "/pByStoreid", method = RequestMethod.POST)
	public @ResponseBody
	String printSelet(String sid) {
		System.out.println("--------------------" + sid);
		String json = "";
		if (sid != null) {
			long sidl = Long.parseLong(sid);
			json = printServer.getAllPrint(sidl);
		}

		return json;
	}

}
