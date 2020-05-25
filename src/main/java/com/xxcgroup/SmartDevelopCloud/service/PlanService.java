package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.EquipBean;
import com.xxcgroup.SmartDevelopCloud.bean.PlanBean;
import com.xxcgroup.SmartDevelopCloud.bean.DealBean;
import com.xxcgroup.SmartDevelopCloud.bean.ScheBean;
import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.*;
import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.enumbean.DealState;
import com.xxcgroup.SmartDevelopCloud.enumbean.PlanState;
import org.springframework.beans.BeanUtils;
import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.BeanUtilsHelper;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    @Autowired
    private DealDao dealDao;
    @Autowired
    private PlanDao planDao;

    public JsonResult<List<PlanBean>> showPlanList(int facid){         //查看计划
        List<Plan> plans=planDao.findAllByFacid(facid);
        List<PlanBean> beans = new ArrayList<>();
        for (Plan p:plans) {
            beans.add(new PlanBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<PlanBean> addPlan(Plan plan){            //新建计划
        Deal deal = dealDao.findById(plan.getDeal().getDealid()).orElse(null);
        if(deal==null)return JsonResult.failMessage("订单不存在");
        plan.setDeal(deal);
        if (deal.getState() == DealState.ACCEPTED.getCode()||deal.getState() == DealState.DOING.getCode()){
            plan.setState(PlanState.UNSTART.getCode());
            deal.setState(DealState.DOING.getCode());
            return JsonResult.success(new PlanBean(planDao.save(plan)));
        }else{
            return JsonResult.failMessage("未接受的订单");
        }
    }
    public JsonResult<PlanBean> startPlan(int planid){          //启动计划
        Plan plan = planDao.findById(planid).orElse(null);
        if (plan == null) return JsonResult.failMessage("计划不存在");
        if(plan.getState() != PlanState.UNSTART.getCode()) return JsonResult.failMessage("已启动或已完成不可启动");
        plan.setState(PlanState.LAUNCHED.getCode());
        return JsonResult.success(new PlanBean(planDao.save(plan)));

    }
    public JsonResult<PlanBean> removePlan(int planid){         //删除计划
        Plan plan = planDao.findById(planid).orElse(null);
        if (plan == null) return JsonResult.failMessage("计划不存在");
        if(plan.getState() == PlanState.LAUNCHED.getCode()) return JsonResult.failMessage("已启动不可删除");
        planDao.delete(plan);
        return JsonResult.successMessage("删除成功");

    }

    public JsonResult<List<PlanBean>> searchPlanList(Integer dealid) {
        List<Plan> plans = planDao.findAllByDeal(dealDao.findById(dealid).orElse(null));
        List<PlanBean> beans = new ArrayList<>();
        for (Plan p:plans) {
            beans.add(new PlanBean(p));
        }
        return JsonResult.success(beans);
    }
}
