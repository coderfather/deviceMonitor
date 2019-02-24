package com.deviceMonitor.model;

public class SysParam {
	private Integer id;
	private String type;
	private String code;
	private String name;
	private String remark;
	
	public SysParam() {
		super();
	}
	
	public SysParam(Integer id, String type, String code, String name, String remark) {
		super();
		this.id = id; 
		this.type = type; 
		this.code = code; 
		this.name = name; 
		this.remark = remark; 
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	} 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 
}
