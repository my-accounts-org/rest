package com.company.ac.models;

public class Group {
	
	private long id;
	private String name;
	private long under;
	private String nature;
	private boolean isDefault;
	private boolean grossAffected;
	
	public Group() {
		
	}	

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", under=" + under + ", nature=" + nature + ", isDefault="
				+ isDefault + ", grossAffected=" + grossAffected + "]";
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isGrossAffected() {
		return grossAffected;
	}

	public void setGrossAffected(boolean grossAffected) {
		this.grossAffected = grossAffected;
	}
	
	
}
