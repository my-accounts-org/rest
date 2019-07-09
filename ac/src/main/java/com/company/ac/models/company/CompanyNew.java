package com.company.ac.models.company;

import java.util.Date;

public class CompanyNew {

	private int id;
	private String name;
	private String mailingName;
	private String email;
		
	private Date financialYear;
	private Date booksBeginingFrom;
	
	private char[] password;
		
	private Address address;

	public CompanyNew() {
		super();		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Date financialYear) {
		this.financialYear = financialYear;
	}

	public Date getBooksBeginingFrom() {
		return booksBeginingFrom;
	}

	public void setBooksBeginingFrom(Date booksBeginingFrom) {
		this.booksBeginingFrom = booksBeginingFrom;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
