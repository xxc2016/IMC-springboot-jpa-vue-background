package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.DealBean;
import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.util.JAXBSource;
import java.util.List;

@RestController
@RequestMapping("/deal")
public class DealController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private DealService dealService;
    @GetMapping("/index")
    public JsonResult<List<DealBean>> showDealList(HttpServletRequest request) {
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return dealService.showDealList(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/add")
    public  JsonResult<DealBean> addDeal(HttpServletRequest request, @RequestBody Deal deal){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode()){
            return dealService.addDeal(facid,deal);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/accept")
    public  JsonResult<DealBean> accDeal(HttpServletRequest request,@RequestParam("dealid")Integer dealid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode()){
            return dealService.accDeal(dealid);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/refuse")
    public  JsonResult<DealBean> refuseDeal(HttpServletRequest request,@RequestParam("dealid")Integer dealid,@RequestParam("memo")String memo){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode()){
            return dealService.refuseDeal(dealid,memo);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/start")
    public  JsonResult<DealBean> dealToPlan(HttpServletRequest request,@RequestParam("dealid")Integer dealid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode()){
            return dealService.dealToPlan(dealid);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/finish")
    public  JsonResult<DealBean> finishDeal(HttpServletRequest request,@RequestParam("dealid")Integer dealid,@RequestParam("memo")String memo){                //完成订单
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode()){
            return dealService.finishDeal(dealid,memo);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
}
