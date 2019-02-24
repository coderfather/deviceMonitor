package com.deviceMonitor.model.syn;

public class ChannelDTO {
	private String resolutionver;
	private ChannelDetailDTO[] data;
	
	public String getResolutionver() {
		return resolutionver;
	}
	public void setResolutionver(String resolutionver) {
		this.resolutionver = resolutionver;
	}
	public ChannelDetailDTO[] getData() {
		return data;
	}
	public void setData(ChannelDetailDTO[] data) {
		this.data = data;
	}
}
