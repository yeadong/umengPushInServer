package com.wap.entity;

public class DeskEntity {
	private Long id;
	private Long storeid;
//	private Long customerid;
	private Integer persin;
	private String dname;
	private Integer dstatus;
	public Integer getDstatus() {
		return dstatus;
	}
	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStoreid() {
		return storeid;
	}
	public void setStoreid(Long storeid) {
		this.storeid = storeid;
	}
//	public Long getCustomerid() {
//		return customerid;
//	}
//	public void setCustomerid(Long customerid) {
//		this.customerid = customerid;
//	}
	public Integer getPersin() {
		return persin;
	}
	public void setPersin(Integer persin) {
		this.persin = persin;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
}
