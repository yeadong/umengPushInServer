package com.wap.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.wap.entity.AliPayBack;
import com.wap.service.WxpublicNumPayService;

@Controller
@RequestMapping("/Aliuser")
public class AliUserCodeController {
	@Autowired
	private WxpublicNumPayService wxPublicNumPayService;

	@RequestMapping(value = "/diancan")
	public ModelAndView diancanAuth(
			@RequestParam(required = false) String auth_code,
			@RequestParam(required = false) Long deskNo, String browser,
			HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		System.err.println("--------------" + auth_code);
		if (deskNo != null && !"".equals(deskNo)) {
			ModelAndView modelAndView = null;
			byte var10 = -1;
			switch (browser.hashCode()) {
			case 70876723:
				if (browser.equals("wx_pay_browser")) {
					var10 = 1;
				}
				break;
			case 1646769520:
				if (browser.equals("ali_pay_browser")) {
					var10 = 0;
				}
			}

			String s;
			switch (var10) {
			case 0:
				s = this.diancan(auth_code);
				response.sendRedirect(s);
				return modelAndView;

			case 1:
				// modelAndView = this.wxPay(code, session, modelAndView,
				// deskNo, orderId, storeUserId);
				// break;
			default:
				modelAndView.addObject("msg", (new Exception()).getMessage());
				modelAndView.setViewName("error/error");
			}

			return modelAndView;
		} else {
			throw new NullPointerException("auth传入code为空");
		}
	}

	private String diancan(String code) {
		try {
			AlipaySystemOauthTokenResponse e = this.wxPublicNumPayService
					.getAliPayCode(code);
			if (e == null || e.getUserId() == null || "".equals(e.getUserId())) {
				throw new NullPointerException("授权信息错误");
			}

			StringBuilder sb = new StringBuilder();
			System.out.println(e.getUserId());

			sb.append(
					"http://weijing.f3322.net:7070/UmengPushServer/OrderWap/getZFBUserID?")
					.append("userInfo=").append(e.getUserId());

			// modelAndView.addObject("userInfo", e.getUserId());

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * @RequestMapping(value = { "/callback" }, produces = {
	 * "text/plain;charset=utf-8" })
	 * 
	 * @ResponseBody public String WxPay(HttpServletRequest request) {
	 * System.err.println("wxpayCallback now..."); XStream xStream =
	 * XStreamUtils.createXStream(WeixinPayConfirm.class); WeixinPayConfirm
	 * result = new WeixinPayConfirm(); result.setReturn_code("FAIL");
	 * 
	 * try { WeixinPayConfirm e = (WeixinPayConfirm) xStream.fromXML(request
	 * .getInputStream()); if (e != null) { if
	 * (!e.getReturn_code().equals("SUCCESS")) { result.setReturn_code("FAIL");
	 * return xStream.toXML(result); }
	 * 
	 * String attach = e.getAttach(); if (attach != null && !"".equals(attach))
	 * { String obj = null;
	 * 
	 * try { JSONObject e1 = JSON.parseObject(attach);
	 * 
	 * obj = e1.getString("haipay.com.self.payType"); } catch (Exception var8) {
	 * var8.printStackTrace(); }
	 * 
	 * if (obj != null && !"".equals(obj) && "QR_reward_pay".equals(obj)) {
	 * this.wxPublicNumPayService.confirmRewardOrder(e); } else {
	 * this.wxPublicNumPayService.confirmOrder(e); } } else {
	 * this.wxPublicNumPayService.confirmOrder(e); }
	 * 
	 * result.setReturn_code("SUCCESS"); } } catch (BaseException var9) {
	 * result.setReturn_msg(var9.getMessage()); } catch (Exception var10) {
	 * var10.printStackTrace(); }
	 * 
	 * return xStream.toXML(result); }
	 */
	@RequestMapping("/AliPay/callback")
	@ResponseBody
	public String AliPay(AliPayBack aliPayBack, String distinguish, String id) {
		System.err.println("alipayCallback now...");
		return "alipayService.payCallback(aliPayBack, distinguish, id)";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView testss(String deskNo, String browser,
			HttpServletResponse hsr, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		long dn = Long.parseLong(deskNo);
		try {
			String backurl = wxPublicNumPayService.DiancanALiPay(dn, browser);
			if (backurl != null && !Objects.equals(backurl, "")) {
				hsr.sendRedirect(backurl); // 重定向
			}
			System.out.println(backurl + deskNo + browser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

}
