package com.wap.entity;

import java.util.Date;

public class OrderDeviceEntity {

	private Long id;
	private Long operId;

	private Date loginDate;

	private String umengToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getUmengToken() {
		return umengToken;
	}

	public void setUmengToken(String umengToken) {
		this.umengToken = umengToken;
	}
}
