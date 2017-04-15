package com.wap.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.wap.dao.OrderMessageMapper;
import com.wap.entity.OrderMessageEntity;

@Service
public class OrderMessageServer {
	@Autowired
	private OrderMessageMapper messageMapper;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");

	public JSONObject backOrderMessge(long storeid) {
		System.out.println(storeid);
		ArrayList<OrderMessageEntity> message = messageMapper
				.findMessageEntities(storeid);
		JSONObject json = new JSONObject();
		try {
			if (message != null) {
				JSONArray eacharray = new JSONArray();
				for (int i = 0; i < message.size(); i++) {
					OrderMessageEntity entity = message.get(i);
					Date date = entity.getCreateTime();
					String sdate = dateFormat.format(date);
					// = entity.getOperId();
					long operId;
					if (entity.getOperId() == null) {
						operId = 0;
					} else {
						operId = entity.getOperId();
					}
					long id = entity.getId();
					int msgType = entity.getMsgType();
					String msgInfo = entity.getMsgInfo();
					JSONObject eachjson = new JSONObject();
					eachjson.put("createTime", sdate);
					eachjson.put("operId", operId);
					eachjson.put("id", id);
					eachjson.put("msgInfo", msgInfo);
					eachjson.put("msgType", msgType);
					eacharray.put(eachjson);
				}
				json.put("back_code", 200);
				json.put("arraylist", eacharray);
				json.put("typescount", message.size());

			} else {
				json.put("back_code", 501);
				json.put("error_msg", "获取失败，没有数据");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;

	}

	private  String appId = "N3CwU4b7VGA4bphVXSIbC8";
	private  String appKey = "oECem00PQx5Zl6ZPby94i1";
	private  String masterSecret = "ZEpvc0k7k69klB40ji6OA5";

	@Transactional
	public String insertMessage(int type, String info, long mid, long sid) {
//		client = new PushClient();
		JSONObject backJson = new JSONObject();
		OrderMessageEntity entity = new OrderMessageEntity();
		entity.setMsgType(type);
		entity.setMsgInfo(info);
		entity.setMerchantId(mid);
		entity.setStoreId(sid);
		entity.setCreateTime(new Date());
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionContent("{\"isscancode\":true,\"soundmsg\":"+info+"}");// 透传的内容
		template.setTransmissionType(2); // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		int bcode = messageMapper.insertMessage(entity);
		if (bcode == 1) {
			ArrayList<Map<String, Object>> deviceEntities = messageMapper
					.queryUmengToken(sid);
			IGtPush push = new IGtPush("http://sdk.open.api.igexin.com/apiex.htm", appKey, masterSecret);// push 接口
			StringBuffer sbuffer = null;
			if (deviceEntities != null) {
				sbuffer = new StringBuffer();
				for (int i = 0; i < deviceEntities.size(); i++) {
					Map<String, Object> map = deviceEntities.get(i);
					String str = (String) map.get("umtoken");
					SingleMessage message = new SingleMessage();
					message.setOffline(true);// 消息离线是否存储
					message.setData(template);// 推送消息消息内容
					message.setPushNetWorkType(0); // 0-任何 1-wifi 2- 2/3/4G
					Target target = new Target();
					target.setAppId(appId);
					target.setClientId(str);// 设备的devicetoken
					IPushResult result = null;
					try {
						result = push.pushMessageToSingle(message, target);
					} catch (RequestException e) {
						result = push.pushMessageToSingle(message, target,
								e.getRequestId());
						System.out.println("发送失败");
					}
					String msg = (result == null) ? "PUSH服务器异常" : result
							.getResponse().toString();
					System.err.println(msg);
				}

				// String str = (result == null) ? "PUSH服务器异常" :
				// result.getResponse()
				// .toString();

				/*
				 * try { AndroidUnicast ac = new AndroidUnicast(appkey,
				 * appMasterSecret); ac.setDeviceToken(sbuffer.toString()); //
				 * ac.setProductionMode(true);// 正式、测试模式 ac.setTitle("消息提醒");
				 * ac.setTicker("消息提醒"); ac.setText(info);
				 * ac.setDisplayType(AndroidNotification
				 * .DisplayType.NOTIFICATION); ac.setPlaySound(true);
				 * ac.setPlayLights(true); ac.setPlayVibrate(true); // boolean
				 * send = client.send(ac); backJson.put("back_code", 200);
				 * backJson.put("sendStatus", "true"); } catch (Exception e) {
				 * e.printStackTrace(); }
				 */
			}

		} else {
			try {
				backJson.put("back_code", 502);
				backJson.put("error_msg", "提交出错");
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return backJson.toString();
	}

//	private PushClient client;
//
//	public void sendPush(String token, String title) {
//		client = new PushClient();
//		token = "AthKVKSGsh_IR-1jHXLiAOUFeuLaW4PF-zCLZZ0WQ-E-";
//		try {
//			AndroidUnicast ac = new AndroidUnicast(appkey, appMasterSecret);
//			ac.setDeviceToken(token);
//			// ac.setProductionMode(true);// 正式、测试模式
//			// ac.setActivity("gjcm.kxf.orderwap.OrderDeatailsActivity");
//			// ac.setAfterOpenAction(AfterOpenAction.go_activity);
//			ac.setTitle("");
//			ac.setTicker("");
//			ac.setText(title);
//			// ac.setExtraField("key", "5");
//			// ac.setExtraField("ss", "ss");
//			// ac.setSound("R.raw.sound");
//			ac.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
//			ac.setPlaySound(true);
//			ac.setPlayLights(true);
//			ac.setPlayVibrate(true);
//			boolean send = client.send(ac);
//			System.out.println("Push " + send);
//			client = null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public String changeStatus(long oid, long mid) {
		String backMsg = "";
		int backcode = messageMapper.changeMessageOper(oid, mid);
		if (backcode == 1) {
			backMsg = "{\"back_code\":200\"}";
		} else {
			backMsg = "{\"back_code\":502,\"error_msg\":\"提交出错\"}";

		}
		return backMsg;
	}

	// public String getDeviceToken(long operid) {
	// ArrayList<Map<String, Object>> deviceEntities = messageMapper
	// .deviceEntities(operid);
	// StringBuffer sbuffer = null;
	// if (deviceEntities != null) {
	// sbuffer = new StringBuffer();
	// for (int i = 0; i < deviceEntities.size(); i++) {
	// Map<String, Object> map = deviceEntities.get(i);
	// String str = (String) map.get("token");
	// sbuffer.append(str + ",");
	// }
	// return sbuffer.toString();
	// } else {
	// return "";
	// }
	//
	// }
}
