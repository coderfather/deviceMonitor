package com.deviceMonitor.model.syn;

public class StationInfoSaveInputDTO extends SynBase {
	private String device;
	private String name;
	private String cel;
	private String tel;
	private String[] data1;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String[] getData1() {
		return data1;
	}
	public void setData1(String[] data1) {
		this.data1 = data1;
	}
}
