package com.company.ac.models;

public class Balance {
	
	private double dr;
	private double cr;
		
	public Balance(double dr, double cr) {
		super();
		this.dr = dr;
		this.cr = cr;
	}

	public double getDr() {
		return dr;
	}

	public void setDr(double dr) {
		this.dr = dr;
	}

	public double getCr() {
		return cr;
	}

	public void setCr(double cr) {
		this.cr = cr;
	}
	
	
	
}
