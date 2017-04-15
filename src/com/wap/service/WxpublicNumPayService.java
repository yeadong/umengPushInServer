package com.wap.service;

import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.wap.entity.AgentAliIsvCommon;
import com.wap.entity.Dictionary;

@Service
public class WxpublicNumPayService {
	// @Autowired
	// private AlipayInterface alipayInterface;
	private String url = "https://openapi.alipay.com/gateway.do";

	public AlipaySystemOauthTokenResponse getAliPayCode(String code)
			throws Exception {
		if (code != null) {

			AgentAliIsvCommon agentAliIsvCommon = Dictionary.AliIsv.get(Long
					.parseLong("1"));
			System.err.println("支付宝支付授权...code:" + code);
			AlipayClient alipayClient = this.initClient(agentAliIsvCommon);
			AlipaySystemOauthTokenRequest alipaySystemOauthTokenRequest = new AlipaySystemOauthTokenRequest();
			alipaySystemOauthTokenRequest.setCode(code);
			alipaySystemOauthTokenRequest.setGrantType("authorization_code");
			AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = null;
			alipaySystemOauthTokenResponse = (AlipaySystemOauthTokenResponse) alipayClient
					.execute(alipaySystemOauthTokenRequest);
			return alipaySystemOauthTokenResponse;
			// return this.alipayInterface.aliPayGetToken(agentAliIsvCommon,
			// code);
		} else {
			throw new NullPointerException("参数错误,授权失败");
		}
	}

	private AlipayClient initClient(AgentAliIsvCommon agentAliIsvCommon) {
		System.err.println("实例支付宝基本参数:Appid" + agentAliIsvCommon.getAppid());
		return new DefaultAlipayClient(this.url, agentAliIsvCommon.getAppid(),
				agentAliIsvCommon.getAliKey(), "json", "UTF-8",
				agentAliIsvCommon.getPublicKey());
	}
	
	
	
	  public String DiancanALiPay(Long deskNo, String browser) throws Exception {
	        byte isv = -1;
	        Long merchantId;
	        System.err.println("通过桌号点餐:" + deskNo);
	        
//	        AgentAliIsvCommon isv1 = (AgentAliIsvCommon)Dictionary.AliIsv.get(Long.parseLong("1"));
	        
	        StringBuffer aliStrUrl1 = new StringBuffer();
	        //alipay.frontUrl=https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=
	        aliStrUrl1.append("https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=").append("2016090201837003").append("&scope=auth_userinfo&redirect_uri=").append(URLEncoder.encode("http://weijing.f3322.net:7070/UmengPushServer/Aliuser/diancan?deskNo=" + deskNo + "&browser=" + browser, "UTF-8"));
	        System.err.println("支付宝授权URL:" + aliStrUrl1.toString());
	        return aliStrUrl1.toString();
	    }
}
