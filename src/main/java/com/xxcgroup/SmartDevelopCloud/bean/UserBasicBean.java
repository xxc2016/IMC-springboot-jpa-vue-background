package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.User;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserBasicBean implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username; //用户名
    private int state;			//权限
    private List<Integer> facid;
    private List<String> facname;		//工厂
    private String name;		//真实姓名
    private String headurl;		//头像url


    public UserBasicBean(User user) {
        this.username = user.getUsername();
        this.state = user.getState();
        this.facid = new ArrayList<>();
        this.facname=new ArrayList<>();
        this.name = user.getName();
        this.headurl=user.getHeadurl();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Integer> getFacid() {
        return facid;
    }

    public void setFacid(List<Integer> facid) {
        this.facid = facid;
    }

    public List<String> getFacname() {
        return facname;
    }

    public void setFacname(List<String> facname) {
        this.facname = facname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
}
