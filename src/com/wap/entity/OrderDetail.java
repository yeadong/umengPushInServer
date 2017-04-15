package com.wap.entity;

import java.util.Date;
import java.util.List;

//SELECT detail.id,  detail.order_id, goods.`name`,detail.goods_num,goods.price,detail.create_time,goods.picture_addr FROM order_order_detail detail INNER JOIN order_goods goods ON detail.goods_id=goods.id WHERE  detail.order_id=2 AND detail.del_flag=1 
public class OrderDetail {
	private Long id;
	private String deskname;
	private Short personNum;
	private Date createTime;
	private Integer orderAmount;
	private String remark;
	private Long customerId;
	private Integer orderStatus;
	private List<OrderDetail> list;
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
	public List<OrderDetail> getList() {
		return list;
	}
	public void setList(List<OrderDetail> list) {
		this.list = list;
	}

	
}
