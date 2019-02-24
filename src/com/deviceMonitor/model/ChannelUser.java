package com.deviceMonitor.model;

public class ChannelUser {
	private Long id;
	private Long device_param_id;
	private Long device_user_id;
	private String is_display;
	private String is_modify;
	
	private Integer device_station_id;
	private String register_number;
	private String device_code;
	private String tag_name;
	private String device_name;
	
	public ChannelUser() {
		super();
	}
	
	public ChannelUser(Long device_param_id, Long device_user_id, String is_display, String is_modify) {
		super();
		this.device_param_id = device_param_id; 
		this.device_user_id = device_user_id; 
		this.is_display = is_display; 
		this.is_modify = is_modify; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDevice_param_id() {
		return device_param_id;
	}

	public void setDevice_param_id(Long device_param_id) {
		this.device_param_id = device_param_id;
	} 
	public Long getDevice_user_id() {
		return device_user_id;
	}

	public void setDevice_user_id(Long device_user_id) {
		this.device_user_id = device_user_id;
	} 
	public String getIs_display() {
		return is_display;
	}

	public void setIs_display(String is_display) {
		this.is_display = is_display;
	} 
	public String getIs_modify() {
		return is_modify;
	}

	public void setIs_modify(String is_modify) {
		this.is_modify = is_modify;
	}
	
	public Integer getDevice_station_id() {
		return device_station_id;
	}

	public void setDevice_station_id(Integer device_station_id) {
		this.device_station_id = device_station_id;
	}

	public String getRegister_number() {
		return register_number;
	}

	public void setRegister_number(String register_number) {
		this.register_number = register_number;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	} 
}
