package com.fei.store.domain;

public class Description {
	private String description;

	public Description() {
		super();
	}
	
	public Description(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "Description [description=" + description + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
