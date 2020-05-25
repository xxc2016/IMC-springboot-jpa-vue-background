package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.DealBean;
import com.xxcgroup.SmartDevelopCloud.bean.PlanBean;
import com.xxcgroup.SmartDevelopCloud.bean.ProductBean;
import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.enumbean.PlanState;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private PlanService planService;
    @GetMapping("/index")
    public JsonResult<List<PlanBean>> showPlanList(HttpServletRequest request){
        int facid = (int) request.getSession().getAttribute("facid");
        int state = (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return planService.showPlanList(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/search")
    public JsonResult<List<PlanBean>> searchPlanList(HttpServletRequest request,@RequestParam("dealid")Integer dealid){
        int state = (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return planService.searchPlanList(dealid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/add")
    public JsonResult<PlanBean> addPlan(HttpServletRequest request, @RequestBody Plan plan){
        int state = (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return planService.addPlan(plan);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/start")
    public JsonResult<PlanBean> startPlan(HttpServletRequest request,@RequestParam("planid")Integer planid){
        int state = (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return planService.startPlan(planid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/remove")
    public JsonResult<PlanBean> removePlan(HttpServletRequest request,@RequestParam("planid")Integer planid){
        int state = (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return planService.removePlan(planid);
        else
            return JsonResult.failMessage("权限不足");
    }
}
