package com.deviceMonitor.constant;

/**
 * 接口Action枚举
 * @author Administrator
 *
 */
public enum ActionEnum {
	/**
	 * 1.	用户注册
	 */
	Register("register", "register"),
	/**
	 * 2.	用户信息修改
	 */
	Modify("modify", "modify"),
	/**
	 * 3.	用户登录
	 */
	Login("login", "login"),
	/**
	 * 4.	获取权限站点信息
	 */
	Station("station", "station"),
	/**
	 * 5.	获取设备点具体通道名称和解析
	 */
	Readver("readver", "readver"),
	/**
	 * 8.	设备站点排序上发
	 */
	Queue("queue", "queue"),
	/**
	 * 9.	对应站点保存一些信息
	 */
	Save("save", "save"),
	/**
	 * 10.	对应站点一些储存信息读取
	 */
	Readsave("readsave", "readsave"),
	/**
	 * 11.  获取手机验证码
	 */
	GetSMSVerCode("getSMSVerCode", "getSMSVerCode"),
	
	ForgetPwd1("forgetPwd1", "forgetPwd1"),
	ForgetPwd2("forgetPwd2", "forgetPwd2");
	
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private ActionEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ActionEnum getValueName(String code) {

        for (ActionEnum c : ActionEnum.values()) {
            if (c.getName().equals(code) || c.getValue().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
