package com.wap.entity;

public class UpdateNumEntity {

	private Long id;
	private int  num;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public UpdateNumEntity(Long id, int num) {
		super();
		this.id = id;
		this.num = num;
	}
}
