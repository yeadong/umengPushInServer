package com.wap.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.wap.dao.KxfCustomerMapper;
import com.wap.dao.OrderOperaterMapper;
import com.wap.entity.OrderOperaterEntity;

@Service
public class WapLoginSer {
	@Resource
	private OrderOperaterMapper operaterMapper;
	@Resource
	private KxfCustomerMapper customerMapper;

	/**
	 * 单语句查询
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	public String WapLoginSer(String mobile, String password) {
		JSONObject json = null;
		String msg = new String("utf-8");
		OrderOperaterEntity operater = operaterMapper.loginOperater(mobile,
				password);
		if (operater != null) {
			json = new JSONObject(operater);
			try {
				json.put("back_code", 200);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			json = new JSONObject();
			try {
				json.put("back_code", 401);
				json.put("error_msg", "用户名密码错误");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		msg = json.toString();
		return msg;
	}

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * login+device update/insert
	 * 
	 * @param phone
	 * @param pwd
	 * @param umeng
	 * @param uuid
	 * @return
	 */
	public String WapLoginSer(String phone, String pwd, String umeng,
			String uuid) {
		System.out.println(phone + "----pwd:" + pwd + "----umeng----" + umeng
				+ "----uuid----" + uuid);
		try {
			byte[] bytes = MessageDigest.getInstance("md5").digest(
					pwd.getBytes());
			StringBuilder hex = new StringBuilder(bytes.length * 2);
			for (byte b : bytes) {
				hex.append(hexDigits[(b >> 4) & 0x0F]);
				hex.append(hexDigits[b & 0x0F]);
			}
			pwd = hex.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.err.println(pwd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uphone", phone);
		map.put("upwd", pwd);
		map.put("utoken", umeng);
		map.put("uuid", uuid);
		map.put("ocunt", 0);
		map.put("mid", 0);
		map.put("sid", 0);
		map.put("lid", 0);
		operaterMapper.updateOperater(map);
		JSONObject backjson = new JSONObject(map);
		return backjson.toString();
	}

	// user_code VARCHAR(50),OUT back_count int,OUT customer_id LONG

	public String addCustomerSer(String usercode) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_code", usercode);
		map.put("back_count", 0);
		map.put("customer_id", 0l);
		customerMapper.addCustomerGetId(map);
		String cid = map.get("customer_id").toString();
		return cid;
	}

	/**
	 * 检测餐桌是否使用
	 * @param deskid
	 * @return
	 */
	public String checkOrderExcit(Long deskid) {
		Map<String, Object> map = new HashMap<>();
		map.put("deskid", deskid);
		map.put("back_count", 0);
		map.put("order_id", 0l);
		customerMapper.excitOrderId(map);
		return map.get("order_id").toString();
	}

}
