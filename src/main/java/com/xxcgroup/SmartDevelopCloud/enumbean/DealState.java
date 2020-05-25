package com.xxcgroup.SmartDevelopCloud.enumbean;

public enum DealState {
    NOORDER(0,"未接单"),
    ACCEPTED(1,"已接受"),
    REFUSED(2,"已拒绝"),
    DOING(3,"加工中"),
    DONE(4,"已完成");
    private int code;
    private String desc;

    DealState(int code, String desc) {
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
