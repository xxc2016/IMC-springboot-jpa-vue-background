package com.xxcgroup.SmartDevelopCloud.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Equiptoproduct;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EquipBean implements Serializable {
    private Integer equipid;		//设备id
    private String equipno;     //设备序列号
    private Integer state;		//设备状态
    private String picurl; 		//图片url

    private List<Integer> products = new ArrayList<>();//产品名
    private List<Integer> num=new ArrayList<>();

    protected EquipBean(){}

    public EquipBean(Equip a) {
        this.equipid = a.getEquipid();
        this.equipno = a.getEquipno();
        this.state = a.getState();
        this.picurl = a.getPicurl();
        for (Equiptoproduct e:a.getProducts()) {
            this.products.add(e.getEquipProductId().getProductid());
            this.num.add(e.getNum());
        }
    }

    public Integer getEquipid() {
        return equipid;
    }

    public void setEquipid(Integer equipid) {
        this.equipid = equipid;
    }

    public String getEquipno() {
        return equipno;
    }

    public void setEquipno(String equipno) {
        this.equipno = equipno;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public List<Integer> getNum() {
        return num;
    }

    public void setNum(List<Integer> num) {
        this.num = num;
    }
}
