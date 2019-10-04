package com.fei.store.domain;

public class User {
	private String uid;
	private String username;
	private String password;
	private String email;
	private String gender;

	
	public User(String uid, String username, String password, String email, String gender) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", gender=" + gender + "]";
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

}
