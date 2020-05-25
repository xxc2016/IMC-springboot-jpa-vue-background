package com.xxcgroup.SmartDevelopCloud.enumbean;

public enum EquipState {
    STOPPING(0,"停用中"),
    RUNNING(1,"加工中"),
    WAITTING(2,"待机中"),
    ERROR(3,"故障");
    private int code;
    private String desc;

    EquipState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
