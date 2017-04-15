package com.wap.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kxf.test.NetUtils;
import com.wap.service.WuYuser;

@Controller
@RequestMapping("/wuyu")
public class WuYucon {
	@Autowired
	private WuYuser wuYuser;

	// public static ConcurrentHashMap<String, HttpServletResponse> respList =
	// new ConcurrentHashMap<String, HttpServletResponse>();

	@RequestMapping(value = "wuyong1", method = RequestMethod.POST)
	public void ttttt1(HttpServletResponse response, HttpServletRequest request) {
		// respList.put("myresp",response);
		// ModelAndView modelAndView;
		try {
			// RequestDispatcher rd = request
			// .getRequestDispatcher("adddevice.jsp");
			// rd.forward(request, response);
			response.sendRedirect("http://weijing.f3322.net:7070/UmengPushServer/wuyu/wuyong2?no=2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// modelAndView=new
		// ModelAndView("http://weijing.f3322.net:7070/UmengPushServer/html/home.html");
		// NetUtils.sendPost(
		// "http://weijing.f3322.net:7070/UmengPushServer/wuyu/wuyong2",
		// "no=2");
		// return modelAndView;
	}

	@RequestMapping(value = "wuyong2")
	public ModelAndView ttttt2(String no, HttpServletResponse response,
			HttpServletRequest request) {
		System.err.println("0----------------------------" + no);
//		try {
//			// response = respList.get("myresp");
//			// response.sendRedirect("adddevice.jsp");
//			RequestDispatcher rd = request
//					.getRequestDispatcher("adddevice.jsp");
//			rd.forward(request, response);
//			// response.sendRedirect("../html/home.html");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return new ModelAndView("adddevice");
	}

}
