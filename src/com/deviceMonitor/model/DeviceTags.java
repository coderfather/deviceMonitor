package com.deviceMonitor.model;

public class DeviceTags {
	private long id;
	private String register_number;
	private String device_code;
	private int resolution_ver;
	private String tag_name;
	private int data_type;
	private int rw_flag;
	private String sNop;
	
	private boolean editable;
	
	public DeviceTags() {
		super();
	}
	
	public DeviceTags(String register_number, String device_code, int resolution_ver, String tag_name, int data_type, int rw_flag, String sNop) {
		super();
		this.register_number = register_number; 
		this.device_code = device_code; 
		this.resolution_ver = resolution_ver; 
		this.tag_name = tag_name; 
		this.data_type = data_type; 
		this.rw_flag = rw_flag; 
		this.sNop = sNop; 
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	public int getResolution_ver() {
		return resolution_ver;
	}

	public void setResolution_ver(int resolution_ver) {
		this.resolution_ver = resolution_ver;
	} 
	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	} 
	public int getData_type() {
		return data_type;
	}

	public void setData_type(int data_type) {
		this.data_type = data_type;
	} 
	public int getRw_flag() {
		return rw_flag;
	}

	public void setRw_flag(int rw_flag) {
		this.rw_flag = rw_flag;
	} 
	public String getsNop() {
		return sNop;
	}

	public void setsNop(String sNop) {
		this.sNop = sNop;
	}

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    } 
}
