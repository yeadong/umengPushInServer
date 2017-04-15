package com.gjcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gjcm.entity.Shops;

@Controller
@RequestMapping("/OrderWap")
public class TestWap {
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	Shops getShopInJSON(@PathVariable String name) {
		System.out.println(name + "-----请求json数据--------");
		Shops shop = new Shops();
		shop.setSmoney(23.632);
		shop.setSname("做idew");
		shop.setSnum(236);
		return shop;
	}

}
