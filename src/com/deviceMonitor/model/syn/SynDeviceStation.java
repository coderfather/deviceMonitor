package com.deviceMonitor.model.syn;

public class SynDeviceStation {
	private String result;
	private String message;
	private int limitver;
	private Object device;
	private Object queue;
	private Object state;
	private Object name;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getLimitver() {
		return limitver;
	}
	public void setLimitver(int limitver) {
		this.limitver = limitver;
	}
	public Object getDevice() {
		return device;
	}
	public void setDevice(Object device) {
		this.device = device;
	}
	public Object getQueue() {
		return queue;
	}
	public void setQueue(Object queue) {
		this.queue = queue;
	}
	public Object getState() {
		return state;
	}
	public void setState(Object state) {
		this.state = state;
	}
	public Object getName() {
		return name;
	}
	public void setName(Object name) {
		this.name = name;
	}
}
