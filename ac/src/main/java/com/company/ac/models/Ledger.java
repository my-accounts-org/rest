package com.company.ac.models;

public class Ledger {
	
	private long id;
	private String name;
	private long under;
	private long openingBalance;
	private String cdDr;
	private String mailingName;
    private String mailingAddress;
    private String fixedName;
    private String ledgerUnderGroupName;
    
	public String getLedgerUnderGroupName() {
		return ledgerUnderGroupName;
	}
	public void setLedgerUnderGroupName(String ledgerUnderGroupName) {
		this.ledgerUnderGroupName = ledgerUnderGroupName;
	}
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
	public long getUnder() {
		return under;
	}
	public void setUnder(long under) {
		this.under = under;
	}
	public long getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getCdDr() {
		return cdDr;
	}
	public void setCdDr(String cdDr) {
		this.cdDr = cdDr;
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
	public String getFixedName() {
		return fixedName;
	}
	public void setFixedName(String fixedName) {
		this.fixedName = fixedName;
	}
}
