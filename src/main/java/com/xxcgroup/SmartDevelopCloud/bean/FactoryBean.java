package com.xxcgroup.SmartDevelopCloud.bean;

import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactoryBean implements Serializable {
    private int facid;        //工厂id
    private String facname;	    //工厂名

    private List<Equip> equips;
    private Map<Integer,Long> equipStates;//加工中、待机中、停机中或故障的设备数量
    private Map<Integer,Long> dealStates;//显示各状态下的订单数量
    private List<MessageBean> messages;
    private Integer messagesCount;

    public FactoryBean(Factory a) {
        this.facid = a.getFacid();
        this.facname = a.getFacname();
        this.equipStates=new HashMap<>();
        this.dealStates=new HashMap<>();
        this.messages=new ArrayList<>();
        this.messagesCount=0;
    }

    public int getFacid() {
        return facid;
    }

    public void setFacid(int facid) {
        this.facid = facid;
    }

    public String getFacname() {
        return facname;
    }

    public void setFacname(String facname) {
        this.facname = facname;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }

    public Map<Integer, Long> getEquipStates() {
        return equipStates;
    }

    public void setEquipStates(Map<Integer, Long> equipStates) {
        this.equipStates = equipStates;
    }

    public Map<Integer, Long> getDealStates() {
        return dealStates;
    }

    public void setDealStates(Map<Integer, Long> dealStates) {
        this.dealStates = dealStates;
    }

    public void addEquipStates(Integer k,Long v){
        this.equipStates.put(k,v);
    }

    public void addDealStates(Integer k,Long v){
        this.dealStates.put(k,v);
    }

    public List<MessageBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBean> messages) {
        this.messages = messages;
        this.messagesCount=messages.size();
    }

    public void addMessages(MessageBean message) {
        this.messages.add(message);
        this.messagesCount+=1;
    }

    public Integer getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(Integer messagesCount) {
        this.messagesCount = messagesCount;
    }
}
