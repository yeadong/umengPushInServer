package com.kxf.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wap.service.WuYuser;

public class tttt {
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) {

//		ApplicationContext ac = new ClassPathXmlApplicationContext(
//				"file:F:/JavaProjects/UmengPushServer/WebContent/WEB-INF/config/spring-dao.xml");
//		for (String name : ac.getBeanDefinitionNames()) {
//			System.err.println(" name: " + name);
//		}
//
//		WuYuser wuYuser = (WuYuser) ac.getBean("wuYuser");
//		System.err.println(" " + wuYuser);
//		wuYuser.addDetailMapper();
		String pwd = "123456";
		try {
			byte[] bytes = MessageDigest.getInstance("md5").digest(
					pwd.getBytes("UTF-8"));
			StringBuilder hex = new StringBuilder(bytes.length * 2);
			for (byte b : bytes) {
				hex.append(hexDigits[(b >> 4) & 0x0F]);
				hex.append(hexDigits[b & 0x0F]);
			}
			pwd = hex.toString();
			System.out.println(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
