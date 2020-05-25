package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.FactoryBean;
import com.xxcgroup.SmartDevelopCloud.bean.MessageBean;
import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.Message;
import com.xxcgroup.SmartDevelopCloud.entity.User;
import com.xxcgroup.SmartDevelopCloud.enumbean.DealState;
import com.xxcgroup.SmartDevelopCloud.enumbean.EquipState;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FactoryService {
    @Autowired
    private FactoryDao factoryDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private DealDao dealDao;
    @Autowired
    private MessageDao messageDao;
    final static String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
    public JsonResult<FactoryBean> facRegister(String facname,String username){//state==0||3
        if (facname.matches(regex)) {
            if(!factoryDao.existsByFacname(facname)){
                User user=userDao.findByUsername(username);
                Factory newfac=new Factory(facname);
                user.addFactory(factoryDao.save(newfac));
                user.setState(UserState.BOSS.getCode());
                userDao.save(user);
                return JsonResult.success(new FactoryBean(newfac));
            }else{
                return JsonResult.failMessage("工厂名已存在");
            }
        } else {
            return JsonResult.failMessage("工厂名不能包含特殊字符");
        }

    }
    public JsonResult<FactoryBean> facLogin(String facname){//state==1||2||3
        Factory fac = factoryDao.findByFacname(facname);
        if(fac==null)
            return JsonResult.failMessage("工厂不存在");
        else {
            FactoryBean bean = new FactoryBean(fac);
            for(int i=0;i<4;i++)
                bean.addEquipStates(i,0L);
            for(int i = 0; i< 5; i++)
                bean.addDealStates(i,0L);
            for (Map<String,Object> m:equipDao.findGroupByEquipStates(fac.getFacid())) {
                bean.addEquipStates((Integer)m.get("state"),(Long)m.get("num"));
            }
            for (Map<String,Object> m:dealDao.findGroupByDealStates(fac.getFacid())) {
                bean.addDealStates((Integer)m.get("state"),(Long)m.get("num"));
            }
            for (Message m:messageDao.findAllByFactory(fac)) {
                bean.addMessages(new MessageBean(m));
            }
            return JsonResult.success(bean);
        }
    }
    public JsonResult<FactoryBean> facInfo(int facid){//state==1||2||3
        Factory fac = factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid));
        if(fac==null)
            return JsonResult.failMessage("工厂不存在");
        else {
            FactoryBean bean = new FactoryBean(fac);
            for (Map<String,Object> m:equipDao.findGroupByEquipStates(fac.getFacid())) {
                bean.addEquipStates((Integer)m.get("state"),(Long)m.get("num"));
            }
            for (Map<String,Object> m:dealDao.findGroupByDealStates(fac.getFacid())) {
                bean.addDealStates((Integer)m.get("state"),(Long)m.get("num"));
            }
            return JsonResult.success(bean);
        }
    }
    public JsonResult<FactoryBean> facJoin(String username,String facname){//state==0
        Factory fac = factoryDao.findByFacname(facname);
        if(fac==null){
            return JsonResult.failMessage("工厂不存在");
        }else {
            User user=userDao.findByUsername(username);
            user.addFactory(fac);
            userDao.save(user);
            return JsonResult.successMessage("申请成功，等待审核通过");
        }
    }
}
