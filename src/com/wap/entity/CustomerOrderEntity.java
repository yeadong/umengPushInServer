package com.wap.entity;

import java.util.Date;

public class CustomerOrderEntity {
	private String orderId;
	private Integer orderStatus;
	private Date nowDate;
	private Integer orderAm;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Integer getOrderAm() {
		return orderAm;
	}

	public void setOrderAm(Integer orderAm) {
		this.orderAm = orderAm;
	}

	public CustomerOrderEntity(String orderId, Integer orderStatus,
			Date nowDate, Integer orderAm) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.nowDate = nowDate;
		this.orderAm = orderAm;
	}

	public CustomerOrderEntity() {
	}

}
