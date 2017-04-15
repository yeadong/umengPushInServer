package com.gjcm.entity;

public class Shops {

	private int snum;
	private String sname;
	private double smoney;
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public double getSmoney() {
		return smoney;
	}
	public void setSmoney(double smoney) {
		this.smoney = smoney;
	}
	public Shops() {
	}
	public Shops(int snum, String sname, double smoney) {
		super();
		this.snum = snum;
		this.sname = sname;
		this.smoney = smoney;
	}
	
	
}
