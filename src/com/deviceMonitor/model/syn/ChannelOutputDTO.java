package com.deviceMonitor.model.syn;

public class ChannelOutputDTO {
	private String result;
	private String message;
	private int resolutionver;
	private String[] channel;
	private String[] name;
	private String[] permission;
	private String[] resolution;
	private int[] dataType;
	
	
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
	public int getResolutionver() {
		return resolutionver;
	}
	public void setResolutionver(int resolutionver) {
		this.resolutionver = resolutionver;
	}
	public String[] getChannel() {
		return channel;
	}
	public void setChannel(String[] channel) {
		this.channel = channel;
	}
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public String[] getPermission() {
		return permission;
	}
	public void setPermission(String[] permission) {
		this.permission = permission;
	}
	public String[] getResolution() {
		return resolution;
	}
	public void setResolution(String[] resolution) {
		this.resolution = resolution;
	}
	public int[] getDataType() {
		return dataType;
	}
	public void setDataType(int[] dataType) {
		this.dataType = dataType;
	}
}
