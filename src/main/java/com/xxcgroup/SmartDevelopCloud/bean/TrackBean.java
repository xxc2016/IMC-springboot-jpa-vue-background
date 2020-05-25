package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import com.xxcgroup.SmartDevelopCloud.entity.Track;

import java.util.Date;
import java.util.List;

public class TrackBean {
    private Integer trackid;    //跟踪id
    private Integer scheid;   //调度id
    private String productname;//产品名称
    private String equipno;		//设备序列号
    private Date start;			//加工开始
    private Date end;			//加工结束
    private Integer num;			//加工数量
    private Integer oknum;		//合格数量
    private Integer finish;		//是否完成
    private Integer schestate;      //调度状态

    public TrackBean(Track a){
        this.trackid = a.getTrackid();
        this.scheid = a.getSche().getScheid();
        this.productname = a.getSche().getPlan().getDeal().getProduct().getProductname();
        this.equipno = a.getSche().getEquip().getEquipno();
        this.start = a.getStart();
        this.end = a.getEnd();
        this.num = a.getNum();
        this.oknum = a.getOknum();
        this.schestate = a.getSche().getState();
        this.finish = a.getFinish();
    }

    public Integer getTrackid() {
        return trackid;
    }

    public void setTrackid(Integer trackid) {
        this.trackid = trackid;
    }

    public Integer getScheid() {
        return scheid;
    }

    public void setScheid(Integer scheid) {
        this.scheid = scheid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public Integer getSchestate() {
        return schestate;
    }

    public void setSchestate(Integer schestate) {
        this.schestate = schestate;
    }
}
