package com.deviceMonitor.model.syn;

public class User extends SynBase {
	private String name;
	private long snumber;
	private int addsum;
	private String company;
	
	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSnumber() {
		return snumber;
	}

	public void setSnumber(long snumber) {
		this.snumber = snumber;
	}

	public int getAddsum() {
		return addsum;
	}

	public void setAddsum(int addsum) {
		this.addsum = addsum;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
