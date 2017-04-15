package com.wap.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wap.service.WapLoginSer;

@Controller
@RequestMapping("/OrderWap")
public class TestWap {

	@Autowired
	private WapLoginSer loginSer;

	// @RequestMapping(value = "{name}", method = RequestMethod.GET)
	// public @ResponseBody
	// Shops getShopInJSON(@PathVariable String name) {
	// System.out.println(name + "-----请求json数据--------");
	// Shops shop = new Shops();
	// shop.setSmoney(23.632);
	// shop.setSname("做idew");
	// shop.setSnum(236);
	// return shop;
	// }
	@RequestMapping(value = "/LoginCon", method = RequestMethod.POST)
	public @ResponseBody
	String LoginCon(String uname, String upwd, String umeng, String uuid) {
		String json = null;
		if (uuid == null) {
			uuid = "uuid";
		}
		if (uname != null && upwd != null && umeng != null) {
			json = loginSer.WapLoginSer(uname, upwd, umeng, "uuid");
		}
		return json;
	}

	@RequestMapping(value = "/testsmaple", method = RequestMethod.POST)
	public @ResponseBody
	String testsmaple(String did) {
		System.out.println(did + "-----------------");
		return "sss";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "home";
	}

	@RequestMapping(value = "/testadd", method = RequestMethod.GET)
	public String testadd(String orderId, ModelMap model) {
		// model.addAttribute("orderId", orderId);
		return "home?orderId=" + orderId;
	}

	@RequestMapping(value = "/FoodOrder", method = RequestMethod.GET)
	public String foodOrder() {
		return "foodOrder";
	}

	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
	public String orderDetail() {
		return "orderDetail";
	}

	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public String orderList() {
		System.out.println("-----------------");
		return "orderList";
	}

	public static void main(String[] args) throws ParseException {
		String sss = URLEncoder
				.encode("http://weijing.f3322.net:7070/UmengPushServer/OrderWap/getAFBUserID");
		System.out.println(sss);
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String s = dateFormat.format(date);
		System.out.println(s);
		date = dateFormat.parse(s);
		System.out.println(date);

	}

	/**
	 * 获取支付宝唯一标识 2016090201837003
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getZFBUserID", method = RequestMethod.GET)
	public ModelAndView getAFBUserID(HttpServletResponse response,
			String userInfo, String deskNo) throws IOException {
		ModelAndView modelAndView;
		if ((userInfo == null) || (userInfo.length() == 0)) {
			modelAndView = new ModelAndView("error");
			modelAndView.addObject("userInfo", "0");
		} else {
			String userid = loginSer.addCustomerSer(userInfo);
			long deskid = Long.parseLong(deskNo);
			String orderid = loginSer.checkOrderExcit(deskid);
			System.err.println("orderid------------"+orderid+"------"+userid);
//			if (orderid.equals("0")) {
				modelAndView = new ModelAndView("home");
				modelAndView.addObject("userInfo", userid);
				modelAndView.addObject("orderid", orderid);

//			} else {
//				modelAndView = new ModelAndView("orderDetail");
//				System.err
//						.println("getAFBUserID---------------------" + orderid);
//			}
		}
		// response.sendRedirect("http://weijing.f3322.net:7070/UmengPushServer/html/home.html");
		return modelAndView;
		// return "redirect:/OrderWap/test";
	}

	@RequestMapping(value = "/welecomeOrder", method = RequestMethod.GET)
	public String welecome(HttpServletRequest request, ModelMap model) {
		String e = request.getHeader("User-Agent");
		boolean wxflag = e.indexOf("MicroMessenger") != -1;
		boolean aliflag = e.indexOf("AliApp") != -1;
		System.out.println("--------request.getParameterMap()--------");
		String deskId = request.getParameter("deskId");
		System.err.println("deskId----------" + deskId);
		Map map = request.getParameterMap();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String[] values = (String[]) map.get(key);
			for (String value : values) {
				System.out.println(key + "=" + value);
			}
		}
		System.out.println("--------request.getHeader()--------");
		// 得到请求头的name集合
		Enumeration<String> em = request.getHeaderNames();
		while (em.hasMoreElements()) {
			String name = (String) em.nextElement();
			String value = request.getHeader(name);
			System.out.println(name + "=" + value);
		}
		model.put("deskid", deskId);
		model.addAttribute("bro", "wxflag: " + wxflag + "  aliflag:" + aliflag);
		return "Welecom";
		// jsp <h3
		// style="margin-top: 100px;">就餐桌位:${requestScope.deskid}在${requestScope.bro}</h3>

	}

}
