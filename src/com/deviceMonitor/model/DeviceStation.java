package com.deviceMonitor.model;

public class DeviceStation {
	private Long id;
	private String register_number;
	private String device_code;
	private String device_name;
	private Integer device_queue;
	private String device_state;
	private Integer max_comm_volume;
	private Integer max_table_volume;
	private Integer user_id;
	private String group1_id;
	private String group2_id;
	private String group3_id;
	private Integer resolution_ver;
	
	private String name;
	private String cel;
	private String tel;
	private String data1;
	
	public DeviceStation() {
		super();
	}
	
	public DeviceStation(Long id, String register_number, String device_code, String device_name, Integer max_comm_volume, Integer max_table_volume, Integer user_id, String group1_id, String group2_id, String group3_id, Integer resolution_ver) {
		super();
		this.id = id; 
		this.register_number = register_number; 
		this.device_code = device_code; 
		this.device_name = device_name; 
		this.max_comm_volume = max_comm_volume; 
		this.max_table_volume = max_table_volume; 
		this.user_id = user_id; 
		this.group1_id = group1_id; 
		this.group2_id = group2_id; 
		this.group3_id = group3_id; 
		this.resolution_ver = resolution_ver; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	} 
	public Integer getMax_comm_volume() {
		return max_comm_volume;
	}
	
	public Integer getDevice_queue() {
		return device_queue;
	}

	public void setDevice_queue(Integer device_queue) {
		this.device_queue = device_queue;
	}

	public String getDevice_state() {
		return device_state;
	}

	public void setDevice_state(String device_state) {
		this.device_state = device_state;
	}

	public void setMax_comm_volume(Integer max_comm_volume) {
		this.max_comm_volume = max_comm_volume;
	} 
	public Integer getMax_table_volume() {
		return max_table_volume;
	}

	public void setMax_table_volume(Integer max_table_volume) {
		this.max_table_volume = max_table_volume;
	} 
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	} 
	public String getGroup1_id() {
		return group1_id;
	}

	public void setGroup1_id(String group1_id) {
		this.group1_id = group1_id;
	} 
	public String getGroup2_id() {
		return group2_id;
	}

	public void setGroup2_id(String group2_id) {
		this.group2_id = group2_id;
	} 
	public String getGroup3_id() {
		return group3_id;
	}

	public void setGroup3_id(String group3_id) {
		this.group3_id = group3_id;
	} 
	public Integer getResolution_ver() {
		return resolution_ver;
	}

	public void setResolution_ver(Integer resolution_ver) {
		this.resolution_ver = resolution_ver;
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

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	} 
}
