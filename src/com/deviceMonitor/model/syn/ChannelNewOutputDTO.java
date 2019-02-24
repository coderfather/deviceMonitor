package com.deviceMonitor.model.syn;

public class ChannelNewOutputDTO {
	private String retCode;
	private String retMsg;
	private ChannelDTO oRet;
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public ChannelDTO getoRet() {
		return oRet;
	}
	public void setoRet(ChannelDTO oRet) {
		this.oRet = oRet;
	}
}
