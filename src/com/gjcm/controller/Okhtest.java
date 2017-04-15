package com.gjcm.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/OkHttpTest")
public class Okhtest {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value = "/TestOne", method = RequestMethod.POST)
	public void TestOne(String uname, Model model) {
		System.out.println("token----》" + request.getHeader("token"));
		model.addAttribute("sname", uname);

	}

	@RequestMapping(value = "/TestGet", method = RequestMethod.GET)
	public void TestGet() {
//		OutputStream outputStream = response.getOutputSt.ream();
//		System.out.println("------in");
	}

}
