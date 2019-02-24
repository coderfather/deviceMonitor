package com.deviceMonitor.model;

public class DeviceUser {
	private Long id;
	private Long device_station_id;
	private Integer user_id;
	private Integer resolution_ver;
	
	private String device_code;
	private String device_name;
	private String group3_id;
	
	public DeviceUser() {
		super();
	}
	
	public DeviceUser(Long id, Long device_station_id, Integer user_id, Integer resolution_ver) {
		super();
		this.id = id; 
		this.device_station_id = device_station_id; 
		this.user_id = user_id; 
		this.resolution_ver = resolution_ver; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
	public Long getDevice_station_id() {
		return device_station_id;
	}

	public void setDevice_station_id(Long device_station_id) {
		this.device_station_id = device_station_id;
	} 
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	} 
	public Integer getResolution_ver() {
		return resolution_ver;
	}

	public void setResolution_ver(Integer resolution_ver) {
		this.resolution_ver = resolution_ver;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getGroup3_id() {
		return group3_id;
	}

	public void setGroup3_id(String group3_id) {
		this.group3_id = group3_id;
	} 
}
