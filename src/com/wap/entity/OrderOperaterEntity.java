package com.wap.entity;

public class OrderOperaterEntity {

	private Long id;
	private String name;
	private String mobile;
	private int owerFlag;
	private Long merchantId;
	private Long storeId;
	private Long onlineFlag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getOwerFlag() {
		return owerFlag;
	}
	public void setOwerFlag(int owerFlag) {
		this.owerFlag = owerFlag;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(Long onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

}
