package com.kxf.sal;


import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.wap.entity.AgentAliIsvCommon;

public interface AlipayInterface {

    AlipaySystemOauthTokenResponse aliPayGetToken(AgentAliIsvCommon var1, String var2) throws Exception;

}
