package com.gjcm.umpush;

import java.text.ParseException;

import com.gjcm.umpush.AndroidNotification.AfterOpenAction;
import com.gjcm.umpush.android.AndroidUnicast;

public class Demo {
	private String appkey = "583931f6b27b0a5f44000ae0";
	private String appMasterSecret = "50k1cgtnh9r82rslc1wabvimqbf8mvit";
	private String timestamp = null;

	private String devicetoken = "AthKVKSGsh_IR-1jHXLiAOVIobmzHVLUbD0wF5iZTTUw";
	private PushClient client = new PushClient();

	public Demo(String key, String secret) {
		try {
			appkey = key;
			appMasterSecret = secret;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);         
		}
	}

	// public void sendAndroidBroadcast() throws Exception {
	// AndroidBroadcast broadcast = new AndroidBroadcast(appkey,
	// appMasterSecret);
	// broadcast.setTicker("只是测试");
	// broadcast.setTitle("测试的标题");
	// broadcast.setText("这是测试内容");
	// broadcast.goAppAfterOpen();
	// broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	// // TODO Set 'production_mode' to 'false' if it's a test device.
	// // For how to register a test device, please see the developer doc.
	// broadcast.setProductionMode();
	//
	// // Set customized fields
	// broadcast.setExtraField("test", "helloworld");
	// client.send(broadcast);
	// }
	//
	// /**
	// * device token 单播
	// *
	// * @throws Exception
	// */
	// public void sendAndroidUnicast() throws Exception {
	// AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
	// // TODO Set your device token
	// unicast.setDeviceToken(devicetoken);
	//
	// unicast.setTicker("Android unicast ticker");
	// unicast.setTitle("中文的title");
	// unicast.setText("Android unicast text");
	// unicast.goAppAfterOpen();
	// unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	// // TODO Set 'production_mode' to 'false' if it's a test device.
	// // For how to register a test device, please see the developer doc.
	// unicast.setProductionMode();
	// // Set customized fields
	// unicast.setExtraField("test", "helloworld");
	// client.send(unicast);
	// }
	//
	// public void sendAndroidGroupcast() throws Exception {
	// AndroidGroupcast groupcast = new AndroidGroupcast(appkey,
	// appMasterSecret);
	// /*
	// * TODO Construct the filter condition: "where": { "and": [
	// * {"tag":"test"}, {"tag":"Test"} ] }
	// */
	// JSONObject filterJson = new JSONObject();
	// JSONObject whereJson = new JSONObject();
	// JSONArray tagArray = new JSONArray();
	// JSONObject testTag = new JSONObject();
	// JSONObject TestTag = new JSONObject();
	// testTag.put("tag", "test");
	// TestTag.put("tag", "Test");
	// tagArray.put(testTag);
	// tagArray.put(TestTag);
	// whereJson.put("and", tagArray);
	// filterJson.put("where", whereJson);
	// System.out.println(filterJson.toString());
	//
	// groupcast.setFilter(filterJson);
	// groupcast.setTicker("Android groupcast ticker");
	// groupcast.setTitle("中文的title");
	// groupcast.setText("Android groupcast text");
	// groupcast.goAppAfterOpen();
	//
	// groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	// // TODO Set 'production_mode' to 'false' if it's a test device.
	// // For how to register a test device, please see the developer doc.
	// groupcast.setProductionMode();
	// client.send(groupcast);
	// }
	//
	// /**
	// * 待办事项
	// *
	// * @throws Exception
	// */
	// public void sendAndroidCustomizedcast() throws Exception {
	// AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(
	// appkey, appMasterSecret);
	// // TODO Set your alias here, and use comma to split them if there are
	// // multiple alias.
	// // And if you have many alias, you can also upload a file containing
	// // these alias, then
	// // use file_id to send customized notification.
	// customizedcast.setAlias("alias", "alias_type");
	// customizedcast.setTicker("Android customizedcast ticker");
	// customizedcast.setTitle("中文的title");
	// customizedcast.setText("Android customizedcast text");
	// customizedcast.goAppAfterOpen();
	// customizedcast
	// .setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	// // TODO Set 'production_mode' to 'false' if it's a test device.
	// // For how to register a test device, please see the developer doc.
	// customizedcast.setProductionMode();
	// client.send(customizedcast);
	// }
	//
	// /**
	// * 多个别名,隔开
	// *
	// * @throws Exception
	// */
	// public void sendAndroidCustomizedcastFile() throws Exception {
	// AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(
	// appkey, appMasterSecret);
	// // TODO Set your alias here, and use comma to split them if there are
	// // multiple alias.
	// // And if you have many alias, you can also upload a file containing
	// // these alias, then
	// // use file_id to send customized notification.
	// String fileId = client.uploadContents(appkey, appMasterSecret, "aa"
	// + "\n" + "bb" + "\n" + "alias");
	// customizedcast.setFileId(fileId, "alias_type");
	// customizedcast.setTicker("Android customizedcast ticker");
	// customizedcast.setTitle("中文的title");
	// customizedcast.setText("Android customizedcast text");
	// customizedcast.goAppAfterOpen();
	// customizedcast
	// .setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	// // TODO Set 'production_mode' to 'false' if it's a test device.
	// // For how to register a test device, please see the developer doc.
	// customizedcast.setProductionMode();
	// client.send(customizedcast);
	// }
	//
	// public void sendAndroidFilecast() throws Exception {
	// AndroidFilecast filecast = new AndroidFilecast(appkey, appMasterSecret);
	// // TODO upload your device tokens, and use '\n' to split them if there
	// // are multiple tokens
	// String fileId = client.uploadContents(appkey, appMasterSecret, "aa"
	// + "\n" + "bb");
	// filecast.setFileId(fileId);
	// filecast.setTicker("Android filecast ticker");
	// filecast.setTitle("中文的title");
	// filecast.setText("Android filecast text");
	// filecast.goAppAfterOpen();
	// filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	// client.send(filecast);
	// }

	public void sendSoundAndroid(String token, String title) {
		AndroidUnicast unicast;
		try {
			unicast = new AndroidUnicast(appkey, appMasterSecret);
			unicast.setDeviceToken(token);
			unicast.setTicker(" unicast ticker");
			unicast.setTitle(title);
			unicast.setText(title);
			unicast.goAppAfterOpen();
			unicast.setPlaySound(true);
			unicast.setPlayLights(true);
			unicast.setPlayVibrate(true);
			unicast.setPlaySound("iphonels");
			unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			unicast.setDescription("金额到账");
			// Set customized fields
			unicast.setExtraField("test", "helloworld");
			client.send(unicast);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendAlis() {
		// AthKVKSGsh_IR-1jHXLiAOUFeuLaW4PF-zCLZZ0WQ-E-
		// AtV00-6geVo98UX56FB399OsrbGrw28vBCJwEwkRnGq6
		// AthKVKSGsh_IR-1jHXLiAOUFeuLaW4PF-zCLZZ0WQ-E-
		String appkey = "58a11247f29d981e00000691", appMasterSecret = "c48xbk7cl2hlhyp6xitwb6lqx31x0eid", token = "AtV00-6geVo98UX56FB399OsrbGrw28vBCJwEwkRnGq6";
		try {

			AndroidUnicast ac = new AndroidUnicast(appkey, appMasterSecret);
			ac.setDeviceToken(token);

			// ac.setProductionMode(true);// 正式、测试模式
			// ac.setActivity("gjcm.kxf.orderwap.OrderDeatailsActivity");
			// ac.setAfterOpenAction(AfterOpenAction.go_activity);
			ac.setTitle("点餐提醒");
			ac.setTicker("接单提醒");
			ac.setText("您有一笔新订单,请接单,     ");
			ac.setExtraField("key", "5");
			ac.setExtraField("ss1", "ss");
			ac.setExtraField("ss2", "sdadee");
			ac.setExtraField("ss3", "sdededcs");
			// ac.setSound("R.raw.sound");
			ac.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			ac.setPlaySound(true);
			ac.setPlayLights(true);
			ac.setPlayVibrate(true);
			client.send(ac);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException {
		// SimpleDateFormat formatter = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String ss = "Wed Feb 08 11:11:00 CST 2017";
		// Timestamp timestamp= Timestamp.valueOf(ss);
		// System.out.println("------>" + timestamp);
		Demo demo = new Demo("", "");
		demo.sendAlis();
		/*
		 * String token = "AthKVKSGsh_IR-1jHXLiAOVIobmzHVLUbD0wF5iZTTUw"; String
		 * title = "支付宝收款8.36元"; try { // demo.sendAndroidUnicast();
		 * 
		 * demo.sendSoundAndroid(token, title);
		 * 
		 * TODO these methods are all available, just fill in some fields and do
		 * the test demo.sendAndroidCustomizedcastFile();
		 * demo.sendAndroidBroadcast(); demo.sendAndroidGroupcast();
		 * demo.sendAndroidCustomizedcast(); demo.sendAndroidFilecast();
		 * 
		 * demo.sendIOSBroadcast(); demo.sendIOSUnicast();
		 * demo.sendIOSGroupcast(); demo.sendIOSCustomizedcast();
		 * demo.sendIOSFilecast();
		 * 
		 * } catch (Exception ex) { ex.printStackTrace(); }
		 */
	}

}
