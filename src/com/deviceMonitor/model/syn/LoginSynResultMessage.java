package com.deviceMonitor.model.syn;

/**
 * 用户登录接口调用返回信息
 *
 */
public class LoginSynResultMessage extends SynResultMessage {
    private String name; 
    private String snumber;
    private String company;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
