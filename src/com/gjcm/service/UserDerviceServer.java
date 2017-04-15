package com.gjcm.service;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjcm.dao.UserDeviceMapper;
import com.gjcm.entity.UserDevice;

@Service
public class UserDerviceServer {
	@Resource
	private UserDeviceMapper deviceMapper;

	/**
	 * storeid token mac imei note 顺序勿乱
	 * 
	 * @param str
	 */
	public int insertDevice(String... str) {
		UserDevice device = new UserDevice();
		int length = str.length;
		long storeid = Long.parseLong(str[0]);
		device.setStoreid(storeid);
		device.setUsertoken(str[1]);
		device.setLoginusername(str[2]);
		device.setDevicetoken(str[3]);
		device.setAppversion(str[4]);
		if (length > 5) {
			device.setDevicemac(str[5]);
		}
		if (length > 6) {
			device.setDeviceimei(str[6]);
		}
		if (length > 7) {
			device.setDevicenote(str[7]);
		}
//		d.logintime= #{logintime,jdbcType=TIMESTAMP},
//				d.storeid= #{storeid,jdbcType=TIMESTAMP},
//				d.usertoken= #{usertoken,jdbcType=TIMESTAMP},
//				d.loginusername= #{loginusername,jdbcType=TIMESTAMP},
//				d.appversion= #{appversion,jdbcType=TIMESTAMP}
		Date date = new Date();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("logintime", date);
		hashMap.put("devicetoken", device.getDevicetoken());
		hashMap.put("storeid", device.getStoreid());
		hashMap.put("usertoken", device.getUsertoken());
		hashMap.put("loginusername", device.getLoginusername());
		hashMap.put("appversion", device.getAppversion());
		
		//hashMap.put("storeid", device.getStoreid());
		int count = deviceMapper.updateUserlogin(hashMap);
		System.out.println("count:====="+count);
		if (count == 0) {
			int code = deviceMapper.insertSelective(device);
			return code;
		} else {
			return count;
		}

	}
	public void getAlldata(){
		
//		deviceMapper.
	}

}
