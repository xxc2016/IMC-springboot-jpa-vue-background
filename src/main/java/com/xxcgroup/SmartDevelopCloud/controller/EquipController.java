package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.EquipBean;
import com.xxcgroup.SmartDevelopCloud.bean.ProductBean;
import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.EquipService;
import com.xxcgroup.SmartDevelopCloud.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equip")
public class EquipController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private EquipService equipService;

    @GetMapping("/index")
    public JsonResult<List<EquipBean>> showEquip(HttpServletRequest request) {
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return equipService.showEquip(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/add")
    public  JsonResult<EquipBean> addEquip(HttpServletRequest request, @RequestBody Param param){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode()){
            return equipService.addEquip(facid,param.equip,param.capacity);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/remove")
    public  JsonResult<EquipBean> removeEquip(HttpServletRequest request,@RequestParam("equipid")int equipid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return equipService.removeEquip(equipid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/update")
    public  JsonResult<EquipBean> updateEquip(HttpServletRequest request,@RequestBody Param param){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return equipService.updateEquip(facid,param.equip,param.capacity);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/search")
    public JsonResult<EquipBean> searchEquip(HttpServletRequest request,@RequestParam("equipno")String equipno){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state!= UserState.NO_FAC.getCode())
            return equipService.searchEquip(facid,equipno);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/error")
    public JsonResult<EquipBean> errorEquip(HttpServletRequest request,@RequestParam("equipid")int equipid){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state!= UserState.NO_FAC.getCode())
            return equipService.errorEquip(facid,equipid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/stop")
    public JsonResult<EquipBean> stopEquip(HttpServletRequest request,@RequestParam("equipid")int equipid){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state!= UserState.NO_FAC.getCode())
            return equipService.stopEquip(facid,equipid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/start")
    public JsonResult<EquipBean> startEquip(HttpServletRequest request,@RequestParam("equipid")int equipid){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state!= UserState.NO_FAC.getCode())
            return equipService.startEquip(facid,equipid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/wait")
    public JsonResult<EquipBean> waitEquip(HttpServletRequest request,@RequestParam("equipid")int equipid){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state!= UserState.NO_FAC.getCode())
            return equipService.waitEquip(facid,equipid);
        else
            return JsonResult.failMessage("权限不足");
    }
}
class Param{
    public Equip equip;
    public Map<Integer,Integer> capacity;
}