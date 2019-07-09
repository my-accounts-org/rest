package com.company.ac.models.company;

public class Security {
	
	enum Maintain {
		ACCOUNTS_ONLY,
		ACCOUNTS_WITH_INVENTORY
	}
	
	private boolean passwordProtected;
	private char[] password;
	private Maintain maintain;
	
	public boolean isPasswordProtected() {
		return passwordProtected;
	}
	
	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public void setPassword(char[] password) {
		this.password = password;
	}

	public Maintain getMaintain() {
		return maintain;
	}

	public void setMaintain(Maintain maintain) {
		this.maintain = maintain;
	}	
	
}
