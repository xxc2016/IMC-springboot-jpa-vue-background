package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Equiptoproduct;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductBean implements Serializable {
    private Integer productid;	//产品id
    private String productname;//产品名
    private String picurl;		//图片url

    private List<Integer> equips=new ArrayList<>();
    private List<Integer> num=new ArrayList<>();

    protected ProductBean(){};

    public ProductBean(Product product) {
        this.productid = product.getProductid();
        this.productname=product.getProductname();
        this.picurl=product.getPicurl();
        for (Equiptoproduct e:product.getEquips()) {
            equips.add(e.getEquipProductId().getEquipid());
            this.num.add(e.getNum());
        }
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
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

    public List<Integer> getEquips() {
        return equips;
    }

    public void setEquips(List<Integer> equips) {
        this.equips = equips;
    }

    public List<Integer> getNum() {
        return num;
    }

    public void setNum(List<Integer> num) {
        this.num = num;
    }
}
