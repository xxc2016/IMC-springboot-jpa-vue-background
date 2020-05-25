package com.xxcgroup.SmartDevelopCloud.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"facid", "productname"}))
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;	//产品id
    @ManyToOne
    @JoinColumn(name = "facid")
    private Factory factory;
    @Column(nullable = false)
    private String productname;//产品名
    private String picurl;		//图片url
    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Equiptoproduct> equips=new ArrayList<>();

    protected Product(){}

    public Product(Integer productid, Factory factory, String productname, String picurl, List<Equiptoproduct> equips) {
        this.productid = productid;
        this.factory = factory;
        this.productname = productname;
        this.picurl = picurl;
        this.equips = equips;
    }

    public Product(String productname) {
        this.productname = productname;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public List<Equiptoproduct> getEquips() {
        return equips;
    }

    public void setEquips(List<Equiptoproduct> equips) {
        this.equips = equips;
    }

}
