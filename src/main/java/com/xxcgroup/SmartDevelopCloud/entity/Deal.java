package com.xxcgroup.SmartDevelopCloud.entity;

import com.xxcgroup.SmartDevelopCloud.enumbean.DealState;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Deal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dealid;		//订单id
    private String dealfrom;	//订单来源
    @OneToOne
    @JoinColumn(name="productid")
    private Product product;//产品名
    private Integer num;			//所需数量
    private Date deadline;		//截止日期
    private Integer state= DealState.NOORDER.getCode();			//订单状态
    private Integer finishnum;		//已完成数量
    private Integer capacity;		//可用产能
    private String memo;        //备注
    @ManyToOne
    @JoinColumn(name = "facid")
    private Factory factory;

    protected Deal(){};

    public Deal(String dealfrom, Product product, Integer num, Date deadline, Integer state, Integer finishnum, Integer capacity) {
        this.dealfrom = dealfrom;
        this.product = product;
        this.num = num;
        this.deadline = deadline;
        this.state = state;
        this.finishnum = finishnum;
        this.capacity = capacity;
    }

    public Integer getDealid() {
        return dealid;
    }

    public void setDealid(Integer dealid) {
        this.dealid = dealid;
    }

    public String getDealfrom() {
        return dealfrom;
    }

    public void setDealfrom(String dealfrom) {
        this.dealfrom = dealfrom;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getFinishnum() {
        return finishnum;
    }

    public void setFinishnum(Integer finishnum) {
        this.finishnum = finishnum;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
