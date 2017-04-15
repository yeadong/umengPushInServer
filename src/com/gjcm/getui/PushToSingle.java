package com.gjcm.getui;

import org.json.JSONException;
import org.json.JSONObject;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class PushToSingle {
	// 采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换 //mn
	private static String appId = "N3CwU4b7VGA4bphVXSIbC8";
	private static String appKey = "oECem00PQx5Zl6ZPby94i1";// 65f77d636bfc616418afbc455f19c1b7
															// 443475138fc853581c0b0b8015c2e910
	private static String masterSecret = "ZEpvc0k7k69klB40ji6OA5";
	public static final String CID = "864bf131c1ea4806b477adf9fa70371e";// 864bf131c1ea4806b477adf9fa70371e//cc52219feff1c198f6353d9652c5f123
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static void main(String[] args) throws Exception {
		String s = getTemplate("864bf131c1ea4806b477adf9fa70371e", "收款6.58元");
		System.out.println(s);
	}

	/**
	 * 推送透传消息
	 * 
	 * @param id
	 * @param soundmsg
	 * @return
	 */
	public static String getTemplate(String id, String soundmsg) {
		JSONObject json = new JSONObject();
		try {
			json.put("isscancode", false);// false 顾客扫付款码推送 true 商户扫顾客支付码
			json.put("soundmsg", soundmsg);// 商户扫支付码 语音播报的内容，下面可以参数可以不添
			// 以下为顾客扫描付款码需传的参
			json.put("isSound", true);// true时添加以下参，false不需添加
			JSONObject soundJson = new JSONObject();
			soundJson.put("soundMsg", "支付宝收款10.26元");// 播报内容
			json.put("soundJson", soundJson);
			json.put("isPrint", true);// true时添加以下参，false不需添加
			JSONObject printJson = new JSONObject();
			printJson.put("orderAmount", 23.69);// 订单金额
			printJson.put("payType", "支付宝");// 支付方式
			printJson.put("payTime", "2017-03-14 15:54:35");// 支付时间
			printJson.put("orderNumber", "201703141554303931939400");// 订单号
			printJson.put("storeName", "康丰超市");// 门店名
			printJson.put("payStatus", "支付成功");// 支付成功
			printJson.put("realPay", 23.00);// 顾客实际支付
			printJson.put("merchantCheques", 23.00);// 商家实收
			printJson.put("merchantDiscount", 23.00);// 商户优惠
			printJson.put("payDiscount", 23.00);// 支付类型优惠
			json.put("printJson", printJson);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionContent(json.toString());// 透传的内容
		template.setTransmissionType(2); // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		IGtPush push = new IGtPush(host, appKey, masterSecret);// push 接口
		SingleMessage message = new SingleMessage();
		message.setOffline(true);// 消息离线是否存储
		message.setData(template);// 推送消息消息内容
		message.setPushNetWorkType(0); // 0-任何  1-wifi  2- 2/3/4G
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(id);// 设备的devicetoken
		IPushResult result = null;
		try {
			result = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			result = push
					.pushMessageToSingle(message, target, e.getRequestId());
			System.out.println("发送失败");
		}
		String str = (result == null) ? "PUSH服务器异常" : result.getResponse()
				.toString();
		return str;
	}

	public String sendMes(String id, String msg) {
		JSONObject json = new JSONObject();
		try {
			json.put("isprint", true);
			json.put("orderam", 23.69);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionType(1);
		Style0 style0 = new Style0();
		style0.setLogo("push.png");
		style0.setTitle("消息提醒");
		style0.setText(msg);
		style0.setLogoUrl("http://merchant.vikpay.com/images/push.png");
		style0.setRing(true);
		style0.setClearable(true);
		style0.setVibrate(true);
		template.setStyle(style0);
		template.setTransmissionContent(json.toString());
		// template.setLogo("push.png");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		message.setData(template);
		message.setPushNetWorkType(0); // 0-任何 1-wifi 2- 2/3/4G
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(id);
		IPushResult result = null;
		try {
			result = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			result = push
					.pushMessageToSingle(message, target, e.getRequestId());
			System.out.println("发送失败");
		}
		String str = (result == null) ? "PUSH服务器异常" : result.getResponse()
				.toString();
		return str;
	}
}