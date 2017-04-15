package com.wap.entity;

public class Testentity {
	private long id;
	private int num;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Testentity(long id, int num) {
		super();
		this.id = id;
		this.num = num;
	}
	
}
