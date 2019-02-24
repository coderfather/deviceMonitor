package com.deviceMonitor.model;

public class DeviceParam {
	private Long id;
	private Long device_station_id;
	private String register_number;
	private String device_code;
	private String tag_name;
	private String display_value;
	private String limit_set;
	private String data_type;
	private String display_name;
	private String display_format;
	private String display_remark;
	
	private String device_name;
	
	public DeviceParam() {
		super();
	}
	
	public DeviceParam(Long id, Long device_station_id, String register_number, String device_code, String tag_name, String display_value, String limit_set, String data_type, String display_name, String display_format, String display_remark) {
		super();
		this.id = id; 
		this.device_station_id = device_station_id; 
		this.register_number = register_number; 
		this.device_code = device_code; 
		this.tag_name = tag_name; 
		this.display_value = display_value; 
		this.limit_set = limit_set; 
		this.data_type = data_type; 
		this.display_name = display_name; 
		this.display_format = display_format; 
		this.display_remark = display_remark; 
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
	public String getDisplay_value() {
		return display_value;
	}

	public void setDisplay_value(String display_value) {
		this.display_value = display_value;
	} 
	public String getLimit_set() {
		return limit_set;
	}

	public void setLimit_set(String limit_set) {
		this.limit_set = limit_set;
	} 
	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	} 
	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	} 
	public String getDisplay_format() {
		return display_format;
	}

	public void setDisplay_format(String display_format) {
		this.display_format = display_format;
	} 
	public String getDisplay_remark() {
		return display_remark;
	}

	public void setDisplay_remark(String display_remark) {
		this.display_remark = display_remark;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	} 
}
