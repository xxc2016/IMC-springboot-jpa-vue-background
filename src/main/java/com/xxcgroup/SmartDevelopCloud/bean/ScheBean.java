package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import com.xxcgroup.SmartDevelopCloud.entity.Track;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ScheBean implements Serializable {
    private Integer scheid;		//调度id(工单id)
    private Integer planid;      //对应计划id
    private Integer dealid;		//订单id
    private String productname;	//订单名
    private Integer dealnum;		//订单所需数量
    private Integer schenum;		//调度要做数量
    private String equipno;		//调度哪台机器
    private Date start;			//开始日期
    private Date end;			//结束日期
    private Integer state;			//状态

    public ScheBean(Sche a){
        this.scheid = a.getScheid();
        this.state = a.getState();
        this.end = a.getEnd();
        this.start = a.getStart();
        this.planid = a.getPlan().getPlanid();
        this.dealid = a.getPlan().getDeal().getDealid();
        this.equipno = a.getEquip().getEquipno();
        this.dealnum = a.getPlan().getDealnum();
        this.schenum = a.getSchenum();
        this.productname = a.getPlan().getDeal().getProduct().getProductname();
    }

    public Integer getScheid() {
        return scheid;
    }

    public void setScheid(Integer scheid) {
        this.scheid = scheid;
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

    public Integer getSchenum() {
        return schenum;
    }

    public void setSchenum(Integer schenum) {
        this.schenum = schenum;
    }

    public String getEquipno() {
        return equipno;
    }

    public void setEquipno(String equipno) {
        this.equipno = equipno;
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
