package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Message;
import com.xxcgroup.SmartDevelopCloud.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class MessageBean implements Serializable {
    private Integer messageid;
    private int type;
    private String content;
    private Date time;

    public MessageBean(Message a) {
        this.messageid = a.getMessageid();
        this.type = a.getType();
        this.content = a.getContent();
        this.time = a.getTime();
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
