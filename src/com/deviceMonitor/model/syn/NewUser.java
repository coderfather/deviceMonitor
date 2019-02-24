package com.deviceMonitor.model.syn;

public class NewUser extends User {
	private String newpass;
	
	public NewUser() {
		super();
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
}
