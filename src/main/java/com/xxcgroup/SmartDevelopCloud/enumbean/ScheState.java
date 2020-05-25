package com.xxcgroup.SmartDevelopCloud.enumbean;

public enum ScheState {
    NOBEGIN(0,"未开始"),
    DOING(1,"加工中"),
    DONE(2,"已完成");
    private int code;
    private String desc;

    ScheState(int code, String desc) {
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
