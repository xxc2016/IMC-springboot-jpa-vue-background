package com.xxcgroup.SmartDevelopCloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageid;
    @ManyToOne
    @JoinColumn(name = "facid")
    private Factory factory;
    private Integer type;
    private String content;
    private Date time;

    protected Message(){};

    public Message(Integer messageid, Factory factory, Integer type, String content, Date time) {
        this.messageid = messageid;
        this.factory = factory;
        this.type = type;
        this.content = content;
        this.time = time;
    }

    public Message(Factory factory, Integer type, String content) {
        this.factory = factory;
        this.type = type;
        this.content = content;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
