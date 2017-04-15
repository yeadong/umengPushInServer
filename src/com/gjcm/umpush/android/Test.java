package com.gjcm.umpush.android;

import org.json.JSONException;
import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws JSONException {
		Test test = new Test();
		test.name(new String[] { "sdasd", "dasas", "asddaae" });
		String appkey = "58a11247f29d981e00000691", appMasterSecret = "c48xbk7cl2hlhyp6xitwb6lqx31x0eid";
		JSONObject policy = new JSONObject();
		policy.put("expire_time", "2017-03-10 12:10:10");
		JSONObject body = new JSONObject();
		body.put("ticker", "hhhhhh");
		body.put("title", "54485");
		body.put("text", "测试文字描述");
		body.put("after_open", "go_app");
		JSONObject payload = new JSONObject();
		payload.put("display_type", "notification");
		payload.put("body", body);
		JSONObject json = new JSONObject();
		json.put("appkey", appkey);
		json.put("timestamp", System.currentTimeMillis());
		json.put("type", "listcast");
		json.put(
				"device_tokens",
				"AtV00-6geVo98UX56FB399OsrbGrw28vBCJwEwkRnGq6,AthKVKSGsh_IR-1jHXLiAOUFeuLaW4PF-zCLZZ0WQ-E-");
		json.put("payload", payload);
		json.put("policy", policy);
		Tools.sendPost("https://msgapi.umeng.com/api/send", json.toString());
//		System.out.println(json);
	}

	private void name(String... strings) {
		System.out.println(strings[0]);
		System.out.println(strings.length);

	}
}
