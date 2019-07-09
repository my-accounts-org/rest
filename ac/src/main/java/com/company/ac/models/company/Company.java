package com.company.ac.models.company;

public class Company {
	private long id;
	private String name;
	private String mailingName;
	private String mailingAddress;
	private String financialYear;
	private String booksBeginingFrom;	
	private String password;
	private boolean passwordProtected;
	private int status;
	
	public Company() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailingName() {
		return mailingName;
	}

	public void setMailingName(String mailingName) {
		this.mailingName = mailingName;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getBooksBeginingFrom() {
		return booksBeginingFrom;
	}

	public void setBooksBeginingFrom(String bookBeginingFrom) {
		this.booksBeginingFrom = bookBeginingFrom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPasswordProtected() {
		return passwordProtected;
	}

	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
