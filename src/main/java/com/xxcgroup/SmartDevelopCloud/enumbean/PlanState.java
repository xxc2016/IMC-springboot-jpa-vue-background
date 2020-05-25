package com.xxcgroup.SmartDevelopCloud.enumbean;

public enum PlanState {
    UNSTART(0,"未启动"),
    LAUNCHED(1,"已启动"),
    FINISHED(2,"计划完成");
    private int code;
    private String desc;

    PlanState(int code, String desc) {
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
