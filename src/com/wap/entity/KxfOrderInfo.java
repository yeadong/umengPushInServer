package com.wap.entity;

import java.util.Date;
//SELECT ooi.id,ooi.order_amount,ooi.remark,ooi.customer_id,ooi.create_time,ooi.person_num,ooi.order_status, 
//desk.name

public class KxfOrderInfo {
	private Long id;
	private Long deskid;
	private String deskname;
	private Short personNum;
	private Date createTime;
	private Integer orderAmount;
	private String remark;
	private Long customerId;
    private Integer orderStatus;   

	public Long getDeskid() {
		return deskid;
	}
	public void setDeskid(Long deskid) {
		this.deskid = deskid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeskname() {
		return deskname;
	}
	public void setDeskname(String deskname) {
		this.deskname = deskname;
	}
	public Short getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Short personNum) {
		this.personNum = personNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	} 

}
