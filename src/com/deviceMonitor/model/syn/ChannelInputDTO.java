package com.deviceMonitor.model.syn;

public class ChannelInputDTO extends SynBase {
	private String device;
	private int resolutionver;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public int getResolutionver() {
		return resolutionver;
	}
	public void setResolutionver(int resolutionver) {
		this.resolutionver = resolutionver;
	}
}
