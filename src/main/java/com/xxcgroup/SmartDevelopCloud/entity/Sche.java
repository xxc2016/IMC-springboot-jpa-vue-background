package com.xxcgroup.SmartDevelopCloud.entity;

import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Sche implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheid;		//调度id(工单id)
    @ManyToOne
    @JoinColumn(name = "planid")
    private Plan plan;      //对应计划id
    private Integer schenum;		//调度要做数量
    @ManyToOne
    @JoinColumn(name = "equipid")
    private Equip equip;		//调度哪台机器
    private Date start;			//开始日期
    private Date end;			//结束日期
    private Integer state= ScheState.NOBEGIN.getCode();			//状态

    protected Sche(){};

    public Sche(Integer scheid, Plan plan, Integer dealid, String productname, Integer dealnum, Integer schenum, Equip equip, Date start, Date end, Integer state) {
        this.scheid = scheid;
        this.plan = plan;
        this.schenum = schenum;
        this.equip = equip;
        this.start = start;
        this.end = end;
        this.state = state;
    }

    public Sche(Plan plan, Integer schenum, Equip equip, Date start, Date end) {
        this.plan = plan;
        this.schenum = schenum;
        this.equip = equip;
        this.start = start;
        this.end = end;
        this.state = 0;
    }

    public Integer getScheid() {
        return scheid;
    }

    public void setScheid(Integer scheid) {
        this.scheid = scheid;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Integer getSchenum() {
        return schenum;
    }

    public void setSchenum(Integer schenum) {
        this.schenum = schenum;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
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
