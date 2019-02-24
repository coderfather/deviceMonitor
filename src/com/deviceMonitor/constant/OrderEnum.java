package com.deviceMonitor.constant;

/**
 * 排序顺序的枚举
 */
public enum OrderEnum {
    ASC("ASC", "ASC"),
    DESC("DESC", "DESC");

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private OrderEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static OrderEnum getValueName(String code) {

        for (OrderEnum c : OrderEnum.values()) {
            if (c.getName().equalsIgnoreCase(code) || c.getValue().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}
