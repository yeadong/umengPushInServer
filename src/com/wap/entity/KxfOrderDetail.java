package com.wap.entity;

import java.util.Date;

public class KxfOrderDetail {
	private Long id;
	private Long orderId;
	private Integer goodsNum;
	private Date createTime;
	private Integer delFlag;
	private String goodName;
	private String pictureAddr;
	private Integer goodPrice;
	private Integer printType;
	public Integer getPrintType() {
		return printType;
	}

	public void setPrintType(Integer printType) {
		this.printType = printType;
	}

	private String goodDesc;

	private Long operid;
	public Long getOperid() {
		return operid;
	}

	public void setOperid(Long operid) {
		this.operid = operid;
	}

	public Integer getGoodstatus() {
		return goodstatus;
	}

	public void setGoodstatus(Integer goodstatus) {
		this.goodstatus = goodstatus;
	}

	private Integer goodstatus;

	public String getGoodDesc() {
		return goodDesc;
	}

	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}

	public Integer getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Integer goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getPictureAddr() {
		return pictureAddr;
	}

	public void setPictureAddr(String pictureAddr) {
		this.pictureAddr = pictureAddr;
	}

}
