package com.deviceMonitor.model;

public class UserGroup {
	private Integer id;
	private Integer user_id;
	
	public UserGroup() {
		super();
	}
	
	public UserGroup(Integer id, Integer user_id) {
		super();
		this.id = id; 
		this.user_id = user_id; 
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	} 
}
