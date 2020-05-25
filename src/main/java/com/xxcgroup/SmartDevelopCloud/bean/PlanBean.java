package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import com.xxcgroup.SmartDevelopCloud.enumbean.PlanState;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlanBean implements Serializable {
    private Integer planid;//计划id
    private Integer dealid;//计划对应订单id
    private String productname;//要生产的产品名
    private Integer dealnum;//订单数量
    private Date start;//计划
    private Date end;//计划结束日期
    private Integer state= PlanState.UNSTART.getCode();			//计划状态

    public PlanBean(Plan a){
        this.dealid = a.getDeal().getDealid();
        this.planid = a.getPlanid();
        this.dealnum = a.getDealnum();
        this.start = a.getStart();
        this.end = a.getEnd();
        this.productname = a.getDeal().getProduct().getProductname();
        this.state = a.getState();
    }

    public Integer getPlanid() {
        return planid;
    }

    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    public Integer getDealid() {
        return dealid;
    }

    public void setDealid(Integer dealid) {
        this.dealid = dealid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getDealnum() {
        return dealnum;
    }

    public void setDealnum(Integer dealnum) {
        this.dealnum = dealnum;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
