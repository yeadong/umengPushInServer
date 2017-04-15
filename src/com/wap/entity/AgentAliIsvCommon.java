package com.wap.entity;

public class AgentAliIsvCommon extends AgentAliIsv {

	private Double prorata;

	public Double getProrata() {
		return prorata;
	}

	public void setProrata(Double prorata) {
		this.prorata = prorata;
	}

	@Override
	public String toString() {
		return "AgentAliIsvCommon{" + "prorata=" + prorata + '}';
	}
}
