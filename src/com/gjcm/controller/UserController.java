package com.gjcm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gjcm.getui.PushToSingle;
import com.gjcm.service.UserDerviceServer;

@Controller
@RequestMapping("/UserDevice")
public class UserController {
	@Autowired
	private UserDerviceServer derviceServer;

	@RequestMapping(value = "/AddDevice", method = RequestMethod.POST)
	public void addDevice(HttpServletRequest req,
			HttpServletResponse httpResponse) {
		String[] str = new String[8];
		str[0] = req.getParameter("storeid");
		str[1] = req.getParameter("dtoken");
		// = req.getParameter("storeName");
		String storename = req.getParameter("storeName");
		System.out.println(storename);
		str[2] = storename;
		str[3] = req.getParameter("umengtoken");
		str[4] = req.getParameter("appversion");
		String gg = req.getParameter("dmac");
		if (gg != null) {
			str[5] = gg;
		}
		gg = req.getParameter("dimei");
		if (gg != null) {
			str[6] = gg;
		}
		gg = req.getParameter("dnote");
		if (gg != null) {
			str[7] = gg;
		}

		int code = derviceServer.insertDevice(str);
		System.out.println(code);
	}

//	private String appkey = "583931f6b27b0a5f44000ae0";
//	private String appMasterSecret = "50k1cgtnh9r82rslc1wabvimqbf8mvit";

	@RequestMapping(value = "/SendDevice", method = RequestMethod.POST)
	public void SendDevice(HttpServletRequest req,
			HttpServletResponse httpResponse) {
		String devicetoken = req.getParameter("devicetoken");
		String msg = req.getParameter("msg");
		if (devicetoken == null) {
			return;
		}
		if (msg == null) {
			return;
		}
		System.out.println(devicetoken+msg);
		PushToSingle push = new PushToSingle();
		String str = push.sendMes(devicetoken, msg);
		System.out.println(str);
//		Map<String, Object> maps = new HashMap<String, Object>();
//		maps.put("isuc", str);
	}

	// Demo demo = new Demo(appkey, appMasterSecret);
	// demo.sendSoundAndroid(devicetoken, msg);

	@RequestMapping("/tojsp")
	public String doJsp() {
		return "adddevice";
	}
}
