package com.deviceMonitor.model;

public class User {
	private Integer id;
	private Integer role_id;
	private String imei;
	private String login_name;
	private String pwd;
	private String user_name;
	private String sex;
	private String phone;
	private String phone_type;
	private String email;
	private String company;
	private String address;
	private String enabled;
	private String remark;
	private Integer parent_id;
	private Integer limit_ver;
	
	public User() {
		super();
	}
	
	public User(Integer id, Integer role_id, String imei, String login_name, String pwd, String user_name, String sex, String phone, String phone_type, String email, String company, String address, String enabled, String remark, Integer parent_id, Integer limit_ver) {
		super();
		this.id = id; 
		this.role_id = role_id; 
		this.imei = imei; 
		this.login_name = login_name; 
		this.pwd = pwd; 
		this.user_name = user_name; 
		this.sex = sex; 
		this.phone = phone; 
		this.phone_type = phone_type; 
		this.email = email; 
		this.company = company; 
		this.address = address; 
		this.enabled = enabled; 
		this.remark = remark; 
		this.parent_id = parent_id; 
		this.limit_ver = limit_ver; 
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		if (role_id == null) {
			role_id = 0;
		}
		this.role_id = role_id;
	} 
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	} 
	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	} 
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	} 
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	} 
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	} 
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	} 
	public String getPhone_type() {
		return phone_type;
	}

	public void setPhone_type(String phone_type) {
		this.phone_type = phone_type;
	} 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	} 
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	} 
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	} 
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 
	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	} 
	public Integer getLimit_ver() {
		return limit_ver;
	}

	public void setLimit_ver(Integer limit_ver) {
		this.limit_ver = limit_ver;
	} 
}
