package com.xxcgroup.SmartDevelopCloud.enumbean;

public enum UserState {
    NO_FAC(0,"未加入工厂"),
    EMPLOYEE(1,"普通员工"),
    MANAGER(2,"生产管理"),
    BOSS(3,"工厂老板");
    private int code;
    private String desc;

    UserState(int code, String desc) {
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
