package com.gjcm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gjcm.entity.HFOrderVersion;
import com.gjcm.service.HFOrderServer;

@Controller
@RequestMapping("/UserDevice")
public class VersionController {
	@Autowired
	private HFOrderServer appsServer;

	@RequestMapping(value = "/getVersion", method = RequestMethod.GET)
	public void getVersion(HttpServletRequest req,
			HttpServletResponse httpResponse) {
		HFOrderVersion appVersion = appsServer.getversion();
		JSONObject jsonObject = new JSONObject(appVersion);
		try {
			PrintWriter out = httpResponse.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
/***
 * {"id":111,"createTime":"Sun Dec 04 11:06:55 CST 2016",
 * "androidVersion":"1","androidVersionName":"1.0.1",
 * "updateTime":"Sun Dec 04 12:04:28 CST 2016",
 * "apkUrl":"http://www.vikpay.com/huifu/release.apk","ipaUrl":""}
 * 
 * 
 */
