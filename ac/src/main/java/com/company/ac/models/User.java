package com.company.ac.models;

public class User {

	private String email;
	private String password;
	private boolean active;
	
	public User() {}
	
	public User(String email, String password, boolean active) {
		super();
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", active=" + active + "]";
	}	
	
	

}
