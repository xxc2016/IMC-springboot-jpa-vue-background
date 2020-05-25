package com.xxcgroup.SmartDevelopCloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Factory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facid;        //工厂id
    @Column(name="facname",unique = true,nullable = false)
    private String facname;	    //工厂名
    @JsonIgnoreProperties(value = {"factories"})
    @ManyToMany(mappedBy = "factories")
    private List<User> users=new ArrayList<>();

    protected Factory(){}

    public Factory(@NotNull String facname) {
        this.facname = facname;
    }

    public Integer getFacid() {
        return facid;
    }

    public void setFacid(Integer facid) {
        this.facid = facid;
    }

    public String getFacname() {
        return facname;
    }

    public void setFacname(String facname) {
        this.facname = facname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
