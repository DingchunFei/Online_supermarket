package com.fei.store.domain;

public class AdminUser extends User {
	
	private String duty;

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Override
	public String toString() {
		return "AdminUser [duty=" + duty + ", toString()=" + super.toString() + "]";
	}
	
}
