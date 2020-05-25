package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.EquipBean;
import com.xxcgroup.SmartDevelopCloud.bean.ScheBean;
import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.*;
import com.xxcgroup.SmartDevelopCloud.enumbean.EquipState;
import com.xxcgroup.SmartDevelopCloud.enumbean.PlanState;
import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheService {
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private FactoryDao factoryDao;
    @Autowired
    private ScheDao scheDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private PlanDao planDao;
    public JsonResult<List<ScheBean>> showScheList(int facid) {         //查看调度
        List<Sche> sches = scheDao.findAllByFacid(facid);
        List<ScheBean> beans=new ArrayList<>();
        for (Sche p:sches) {
            beans.add(new ScheBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<List<ScheBean>> searchScheList(int planid) {         //查看调度
        List<Sche> sches = scheDao.findAllByPlan(planDao.findById(planid).orElse(null));
        List<ScheBean> beans=new ArrayList<>();
        for (Sche p:sches) {
            beans.add(new ScheBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<ScheBean> addSche(int facid, Sche sche) {            //新建调度
        Plan plan = planDao.findById(sche.getPlan().getPlanid()).orElse(null);
        if(plan==null)return JsonResult.failMessage("计划不存在");
        sche.setPlan(plan);
        if (plan.getState() == PlanState.LAUNCHED.getCode()) {
            Equip equip=equipDao.findByFactoryAndEquipno(factoryDao.findById(facid).orElse(null),sche.getEquip().getEquipno());
            if(equip==null)return JsonResult.failMessage("设备不存在");
            if(equip.getState()==EquipState.ERROR.getCode()) return JsonResult.failMessage("该设备已故障");
            sche.setEquip(equip);
            return JsonResult.success(new ScheBean(scheDao.save(sche)));
        }else{
            return JsonResult.failMessage("未启动计划");
    }
    }
    public JsonResult<ScheBean> startSche(int scheid) {             //启动调度
        Sche sche = scheDao.findById(scheid).orElse(null);
        if (sche ==null) return JsonResult.failMessage("工单不存在");
        if (sche.getState() == ScheState.NOBEGIN.getCode()){
            sche.setState(ScheState.DOING.getCode());
            sche.getEquip().setState(EquipState.RUNNING.getCode());
            return JsonResult.success(new ScheBean(scheDao.save(sche)));
        }else{
            return JsonResult.failMessage("调度已启动或已完成");
        }
    }
    public JsonResult<ScheBean> removeSche(int scheid) {            //删除调度
        Sche sche = scheDao.findById(scheid).orElse(null);
        if (sche ==null) return JsonResult.failMessage("工单不存在");
        if(sche.getState() == ScheState.DOING.getCode()) return JsonResult.failMessage("工单已启动，不可删除");
        if(sche.getState() == ScheState.DONE.getCode()) return JsonResult.failMessage("工单已完成，不可删除");
        scheDao.delete(sche);
        return JsonResult.successMessage("删除成功");
    }
    public JsonResult<List<EquipBean>> usefulEquips(int facid,String productname){
        Product product = productDao.findByFactoryAndProductname(factoryDao.findById(facid).orElse(null),productname);
        if(product==null)return JsonResult.failMessage("产品不存在");
        List<EquipBean> beans=new ArrayList<>();
        for (Equiptoproduct e:product.getEquips()) {
            if(e.getEquip().getState() == EquipState.WAITTING.getCode())
                beans.add(new EquipBean(e.getEquip()));
        }
        return JsonResult.success(beans);
    }
}
