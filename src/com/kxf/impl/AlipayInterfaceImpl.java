package com.kxf.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.kxf.sal.AlipayInterface;
import com.wap.entity.AgentAliIsvCommon;


public class AlipayInterfaceImpl implements AlipayInterface  {
    private String url="https://openapi.alipay.com/gateway.do";
    private String returnURL="http://weijing.f3322.net:9090/testSSM/api/AliPay/callback";


    public AlipaySystemOauthTokenResponse aliPayGetToken(AgentAliIsvCommon agentAliIsvCommon, String code) throws AlipayApiException {
        System.err.println("支付宝支付授权...code:" + code);
        AlipayClient alipayClient = this.initClient(agentAliIsvCommon);
        AlipaySystemOauthTokenRequest alipaySystemOauthTokenRequest = new AlipaySystemOauthTokenRequest();
        alipaySystemOauthTokenRequest.setCode(code);
        alipaySystemOauthTokenRequest.setGrantType("authorization_code");
        AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = null;
        alipaySystemOauthTokenResponse = (AlipaySystemOauthTokenResponse)alipayClient.execute(alipaySystemOauthTokenRequest);
        return alipaySystemOauthTokenResponse;
    }


    private AlipayClient initClient(AgentAliIsvCommon agentAliIsvCommon) {
    	 System.err.println("实例支付宝基本参数:Appid" + agentAliIsvCommon.getAppid());
        return new DefaultAlipayClient(this.url, agentAliIsvCommon.getAppid(), agentAliIsvCommon.getAliKey(), "json", "UTF-8", agentAliIsvCommon.getPublicKey());
    }
}
