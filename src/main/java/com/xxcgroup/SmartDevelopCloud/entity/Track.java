package com.xxcgroup.SmartDevelopCloud.entity;

import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
@Entity
public class Track implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackid;		//跟踪id
    @ManyToOne
    @JoinColumn(name = "scheid")
    private Sche sche;		//调度id
    private Date start;			//加工开始
    private Date end;			//加工结束
    private Integer num;			//加工数量
    private Integer oknum;		//合格数量
    private Integer finish=0;		//是否完成

    protected Track(){};

    public Track(Sche sche, Date start, Date end, Integer num, Integer oknum, Integer finish) {
        this.sche = sche;
        this.start = start;
        this.end = end;
        this.num = num;
        this.oknum = oknum;
        this.finish = finish;
    }

    public Integer getTrackid() {
        return trackid;
    }

    public void setTrackid(Integer trackid) {
        this.trackid = trackid;
    }

    public Sche getSche() {
        return sche;
    }

    public void setSche(Sche sche) {
        this.sche = sche;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getOknum() {
        return oknum;
    }

    public void setOknum(Integer oknum) {
        this.oknum = oknum;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }
}
