package com.xxcgroup.SmartDevelopCloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    private String username; //用户名
    @Column(nullable = false)
    private String password;	//密码
    private Integer state= UserState.NO_FAC.getCode();			//权限
    private String name;		//真实姓名
    @Email
    private String mail;        //邮箱
    private Integer sex;			//性别
    private Integer age;			//年龄
    private String headurl;		//头像url

    @JsonIgnoreProperties(value = {"users"})
    @ManyToMany
    @JoinTable(name = "usertofactory",joinColumns = @JoinColumn(name = "username",referencedColumnName = "username"),inverseJoinColumns = @JoinColumn(name = "facid",referencedColumnName = "facid"))
    private List<Factory> factories=new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String name, @Email String mail, Integer sex, Integer age, String headurl, List<Factory> factories) {
        this.username = username;
        this.name = name;
        this.mail = mail;
        this.sex = sex;
        this.age = age;
        this.headurl = headurl;
        this.factories = factories;
    }

    protected User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public List<Factory> getFactories() {
        return factories;
    }

    public void setFactories(List<Factory> factories) {
        this.factories = factories;
    }

    public void addFactory(Factory factory){
        factories.add(factory);
        factory.getUsers().add(this);
    }

    public void removeFactory(Factory factory){
        for (Iterator<Factory> iterator = factories.iterator();
             iterator.hasNext(); ) {
            Factory f = iterator.next();
            if (f.getFacid() .equals( factory.getFacid()) ){
                iterator.remove();
                factory.getUsers().remove(this);
            }
        }
    }
}
