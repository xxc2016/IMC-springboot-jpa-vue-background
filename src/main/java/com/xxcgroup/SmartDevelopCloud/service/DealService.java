//line 92 String型转换为Date型
package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.DealBean;
import com.xxcgroup.SmartDevelopCloud.bean.EquipBean;
import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.*;
import com.xxcgroup.SmartDevelopCloud.enumbean.DealState;
import com.xxcgroup.SmartDevelopCloud.enumbean.EquipState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Service
public class DealService {
    @Autowired
    private FactoryDao factoryDao;
    @Autowired
    private DealDao dealDao;
    @Autowired
    private ProductDao productDao;

    public JsonResult<List<DealBean>> showDealList(int facid) {            //查看订单
        List<Deal> deals = dealDao.findAllByFactory(factoryDao.findById(facid).orElse(null));
        for (Deal d:deals) {
            d.setCapacity(updateCapacity(d));
        }
        List<DealBean> beans = new ArrayList<>();
        for (Deal p:deals) {
            beans.add(new DealBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<DealBean> addDeal(int facid, Deal a) {            //新建订单
        Factory factory = factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid));
        a.setFactory(factory);
        a.setDealfrom("线下");
        a.setState(DealState.NOORDER.getCode());
        a.setFinishnum(0);
        Product product=productDao.findByFactoryAndProductname(factory,a.getProduct().getProductname());
        if(product==null)return JsonResult.failMessage("产品不存在");
        a.setProduct(product);
        a.setCapacity(updateCapacity(a));
        return JsonResult.success(new DealBean(dealDao.save(a)));
    }
    public JsonResult<DealBean> accDeal(int dealid) {               //接受订单
        Deal deal = dealDao.findById(dealid).orElse(null);
        if(deal==null)return JsonResult.failMessage("订单不存在");
        if (deal.getState() == DealState.NOORDER.getCode()) {
            deal.setCapacity(updateCapacity(deal));
            if (deal.getCapacity() < deal.getNum()) {
                return JsonResult.failMessage("产能不足");
            } else {
                deal.setState(DealState.ACCEPTED.getCode());
                return JsonResult.success(new DealBean(dealDao.save(deal)));
            }
        }else{
            return JsonResult.failMessage("接受失败");
        }
    }
    public JsonResult<DealBean> refuseDeal(int dealid,String memo) {             //拒绝订单
        Deal deal = dealDao.findById(dealid).orElse(null);
        if(deal==null)return JsonResult.failMessage("订单不存在");
        if (deal.getState() == DealState.NOORDER.getCode()) {
            deal.setState(DealState.REFUSED.getCode());
            deal.setMemo(memo);
            return JsonResult.success(new DealBean(dealDao.save(deal)));
        } else{
            return JsonResult.failMessage("拒绝失败");
        }
    }
    public JsonResult<DealBean> finishDeal(int dealid,String memo) {            //完成订单
        Deal deal = dealDao.findById(dealid).orElse(null);
        if(deal==null)return JsonResult.failMessage("订单不存在");
        deal.setState(DealState.DONE.getCode());
        deal.setMemo(memo);
        return JsonResult.success(new DealBean(dealDao.save(deal)));

    }
    public  JsonResult<DealBean> dealToPlan(int dealid){            //转成计划
        Deal deal = dealDao.findById(dealid).orElse(null);
        if(deal==null)return JsonResult.failMessage("订单不存在");
        if (deal.getState() == DealState.ACCEPTED.getCode()){
            deal.setState(DealState.DOING.getCode());
            deal.setCapacity(updateCapacity(deal));
            return JsonResult.success(new DealBean(dealDao.save(deal)));
        }else{
            return JsonResult.failMessage("失败，未接受的订单");
        }
    }

    public int updateCapacity(Deal deal){
        int capacity=0;
        for (Equiptoproduct e:deal.getProduct().getEquips()) {
            if (e.getEquip().getState()== EquipState.WAITTING.getCode())
                capacity+=e.getNum();
        }
        return capacity;
    }
}
