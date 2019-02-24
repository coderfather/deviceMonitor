package com.deviceMonitor.model.syn;

public class LoginUser extends SynBase {
	private long pnumber;
	private String type;
	private String address;
	
	private String limitver;
	
	public LoginUser() {
		super();
	}

	public long getPnumber() {
		return pnumber;
	}

	public void setPnumber(long pnumber) {
		this.pnumber = pnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLimitver() {
		return limitver;
	}

	public void setLimitver(String limitver) {
		this.limitver = limitver;
	}
}
