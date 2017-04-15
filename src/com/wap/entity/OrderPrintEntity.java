package com.wap.entity;

public class OrderPrintEntity {
	
	private Long id;
	private String name;
	private Integer printMode;
	private String IP;
	private Integer port;
	private Long storeId;
	private Integer delFlag;
	private Integer printType;
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
	public Integer getPrintMode() {
		return printMode;
	}
	public void setPrintMode(Integer printMode) {
		this.printMode = printMode;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getPrintType() {
		return printType;
	}
	public void setPrintType(Integer printType) {
		this.printType = printType;
	}
	
}
