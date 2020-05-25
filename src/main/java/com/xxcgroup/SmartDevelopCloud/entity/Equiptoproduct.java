package com.xxcgroup.SmartDevelopCloud.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Equiptoproduct implements Serializable {
    @EmbeddedId
    private EquipProductId equipProductId;

    @ManyToOne
    @MapsId("productid")
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @MapsId("equipid")
    @JoinColumn(name = "equipid")
    private Equip equip;
    private int num;
    public Equiptoproduct(){}

    public Equiptoproduct(Product product, Equip equip, int num) {
        this.product = product;
        this.equip = equip;
        this.num = num;
        this.equipProductId = new EquipProductId(product.getProductid(), equip.getEquipid());
    }

    public EquipProductId getEquipProductId() {
        return equipProductId;
    }

    public void setEquipProductId(EquipProductId equipProductId) {
        this.equipProductId = equipProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Equiptoproduct that = (Equiptoproduct) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(equip, that.equip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, equip);
    }
}
