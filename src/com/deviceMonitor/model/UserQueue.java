package com.deviceMonitor.model;

public class UserQueue {
	private Integer user_id;
	private String queue;
	
	public UserQueue() {
		super();
	}
	
	public UserQueue(Integer user_id, String queue) {
		super();
		this.user_id = user_id; 
		this.queue = queue; 
	}
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	} 
	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	} 
}
