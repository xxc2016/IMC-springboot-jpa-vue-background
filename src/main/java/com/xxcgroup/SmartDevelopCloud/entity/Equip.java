package com.xxcgroup.SmartDevelopCloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xxcgroup.SmartDevelopCloud.dao.EquiptoproductDao;
import com.xxcgroup.SmartDevelopCloud.enumbean.EquipState;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"facid", "equipno"}))
public class Equip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipid;		//设备id
    @Column(nullable = false)
    private String equipno;     //设备序列号
    private Integer state= EquipState.STOPPING.getCode();		//设备状态
    private String picurl; 		//图片url
    @JsonIgnoreProperties(value = {"equips"})
    @OneToMany(mappedBy="equip",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Equiptoproduct> products = new ArrayList<>();//产品名
    @ManyToOne
    @JoinColumn(name = "facid")
    private Factory factory;

    protected Equip(){}

    public Equip(Integer equipid, String equipno, Integer state, String picurl, List<Equiptoproduct> products, Factory factory) {
        this.equipid = equipid;
        this.equipno = equipno;
        this.state = state;
        this.picurl = picurl;
        this.products = products;
        this.factory = factory;
    }

    public Equip(Factory factory, String equipno) {
        this.factory=factory;
        this.equipno = equipno;
//        this.state=null;
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

    public List<Equiptoproduct> getProducts() {
        return products;
    }

    public void setProducts(List<Equiptoproduct> products) {
        this.products = products;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void addProduct(Product product,int num) {
        Equiptoproduct equiptoproduct = new Equiptoproduct(product,this,num);
        products.add(equiptoproduct);
        product.getEquips().add(equiptoproduct);
    }

    public void removeProduct(Product product) {
        for (Iterator<Equiptoproduct> iterator = products.iterator();
             iterator.hasNext(); ) {
            Equiptoproduct equiptoproduct = iterator.next();

            if (equiptoproduct.getEquip().equals(this) &&
                    equiptoproduct.getProduct().equals(product)) {
                iterator.remove();
                equiptoproduct.getProduct().getEquips().remove(equiptoproduct);
                equiptoproduct.setProduct(null);
                equiptoproduct.setEquip(null);
            }
        }
    }

    public void removeAllProduct() {
        for (Iterator<Equiptoproduct> iterator = products.iterator();
             iterator.hasNext(); ) {
            Equiptoproduct equiptoproduct = iterator.next();

            if (equiptoproduct.getEquip().equals(this)) {
                iterator.remove();
                equiptoproduct.getProduct().getEquips().remove(equiptoproduct);
                equiptoproduct.setProduct(null);
                equiptoproduct.setEquip(null);
            }
        }
    }

}
