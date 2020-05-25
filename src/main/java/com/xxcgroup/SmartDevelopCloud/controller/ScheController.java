package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.EquipBean;
import com.xxcgroup.SmartDevelopCloud.bean.ScheBean;
import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.ScheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sche")
public class ScheController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ScheService scheService;
    @GetMapping("/index")
    public JsonResult<List<ScheBean>> showScheList(HttpServletRequest request){
        int facid = (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return scheService.showScheList(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/search")
    public JsonResult<List<ScheBean>> searchScheList(HttpServletRequest request,@RequestParam("planid")Integer planid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return scheService.searchScheList(planid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/add")
    public JsonResult<ScheBean> addSche(HttpServletRequest request, @RequestBody Sche sche){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return scheService.addSche(facid,sche);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/start")
    public JsonResult<ScheBean> startSche(HttpServletRequest request,@RequestParam("scheid")Integer scheid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return scheService.startSche(scheid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/remove")
    public JsonResult<ScheBean> removeSche(HttpServletRequest request,@RequestParam("scheid")Integer scheid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return scheService.removeSche(scheid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/usefulEquip")
    public JsonResult<List<EquipBean>> usefulEquip(HttpServletRequest request, @RequestParam("productname") String productname){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.MANAGER.getCode()||state== UserState.BOSS.getCode())
            return scheService.usefulEquips(facid,productname);
        else
            return JsonResult.failMessage("权限不足");
    }
}
