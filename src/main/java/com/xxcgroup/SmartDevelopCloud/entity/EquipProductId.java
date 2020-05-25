package com.xxcgroup.SmartDevelopCloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EquipProductId implements Serializable {
    @Column(name="productid")
    private Integer productid;

    @Column(name="equipid")
    private Integer equipid;
    protected EquipProductId() {};

    public EquipProductId(Integer productid, Integer equipid) {
        this.productid = productid;
        this.equipid = equipid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getEquipid() {
        return equipid;
    }

    public void setEquipid(Integer equipid) {
        this.equipid = equipid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        EquipProductId that = (EquipProductId) o;
        return Objects.equals(productid, that.productid) &&
                Objects.equals(equipid, that.equipid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipid, productid);
    }
}
