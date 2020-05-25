package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.enumbean.DealState;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DealBean {
    private Integer dealid;		//订单id
    private String dealfrom;	//订单来源
    private String productname;//产品名
    private Integer num;			//所需数量
    private Date deadline;		//截止日期
    private Integer state;			//订单状态
    private Integer finishnum;		//已完成数量
    private Integer capacity;		//可用产能
    private String memo;

    public DealBean(Deal a) {
        this.dealid = a.getDealid();
        this.dealfrom = a.getDealfrom();
        this.productname = a.getProduct().getProductname();
        this.num = a.getNum();
        this.deadline = a.getDeadline();
        this.state = a.getState();
        this.finishnum = a.getFinishnum();
        this.capacity = a.getCapacity();
        this.memo = a.getMemo();
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

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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
}
