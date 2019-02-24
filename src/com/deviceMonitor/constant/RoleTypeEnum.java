package com.deviceMonitor.constant;

/**
 * 成功/失败枚举
 * @author Administrator
 *
 */
public enum RoleTypeEnum {
    ITAdmin("ITAdmin", "0"),
    SystemAdmin("SystemAdmin", "1"),
    DeptAdmin("DeptAdmin", "2"),
    Head("Head", "3"),
    Doctor("Doctor", "4");

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private RoleTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static RoleTypeEnum getValueName(String code) {

        for (RoleTypeEnum c : RoleTypeEnum.values()) {
            if (c.getName().equals(code) || c.getValue().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
