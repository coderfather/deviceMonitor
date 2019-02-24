package com.deviceMonitor.constant;

/**
 * 成功/失败枚举
 * @author Administrator
 *
 */
public enum ResultEnum {
	/**
	 * 正常
	 */
	Success("success", "0"),
	/**
	 * 其他地方登录
	 */
	Fail_negative1("error", "-1"),
	/**
	 * 没有权限数据
	 */
	Fail_negative2("error", "-2"),
	/**
	 * 解析版本错误
	 */
	Fail_negative3("error", "-3");

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private ResultEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ResultEnum getValueName(String code) {

        for (ResultEnum c : ResultEnum.values()) {
            if (c.getName().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
