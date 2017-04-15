package com.wap.entity;

import java.util.List;

public class TestList {
	private List<Testentity> jsono;

	public List<Testentity> getJsono() {
		return jsono;
	}

	public void setJsono(List<Testentity> jsono) {
		this.jsono = jsono;
	}

	public TestList(List<Testentity> jsono) {
		super();
		this.jsono = jsono;
	}

	public TestList() {
		super();
	}
	
}
