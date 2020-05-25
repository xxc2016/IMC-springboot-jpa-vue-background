package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class UserDetailBean implements Serializable {
    private String mail;        //邮箱
    private int sex;			//性别
    private int age;			//年龄
    private String headurl;		//头像url
    private String name;		//真实姓名

    public UserDetailBean(User a) {
        this.mail = a.getMail();
        this.sex = a.getSex();
        this.age = a.getAge();
        this.headurl = a.getHeadurl();
        this.name = a.getName();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
