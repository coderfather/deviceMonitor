package com.deviceMonitor.model;

import java.util.Date;

/**
 * 手机验证码
 * @author Administrator
 *
 */
public class VerificationCode {
	private long mobileNumber;
	private int verificationCode;
	private int type;
	private Date createTime;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	} 
}
