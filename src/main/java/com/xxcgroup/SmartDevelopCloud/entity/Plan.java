package com.xxcgroup.SmartDevelopCloud.entity;

import com.xxcgroup.SmartDevelopCloud.enumbean.PlanState;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Plan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planid;//计划id
    @ManyToOne
    @JoinColumn(name = "dealid",nullable = false)
    private Deal deal;//计划对应订单id
    private Integer dealnum;//订单数量
    private Date start;//计划开始日期
    private Date end;//计划结束日期
    private Integer state = PlanState.UNSTART.getCode();

    protected Plan(){};

    public Plan(Deal deal, Integer dealnum, Date start, Date end) {
        this.deal = deal;
        this.dealnum = dealnum;
        this.start = start;
        this.end = end;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPlanid() {
        return planid;
    }

    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
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
}
