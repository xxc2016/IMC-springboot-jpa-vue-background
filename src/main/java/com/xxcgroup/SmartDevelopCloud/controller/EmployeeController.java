package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.UserBasicBean;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.EmployeeService;
import com.xxcgroup.SmartDevelopCloud.service.FactoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/index")
    public JsonResult<List<UserBasicBean>> employeeInfo(HttpServletRequest request){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return employeeService.employeeInfo(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/remove")
    public JsonResult<UserBasicBean> employeeRemove(HttpServletRequest request, @RequestParam("username")String username){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        String bossname = request.getSession().getAttribute("username").toString();
        if(bossname.equals(username))return JsonResult.failMessage("不能开除自己");
        if(state== UserState.BOSS.getCode())
            return employeeService.employeeRemove(facid,username);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/add")
    public JsonResult<UserBasicBean> employeeAdd(HttpServletRequest request,@RequestParam("username")String username){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return employeeService.employeeAdd(facid,username);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/promote")
    public JsonResult<UserBasicBean> employeePromote(HttpServletRequest request,@RequestParam("username")String username){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return employeeService.employeePromote(facid,username);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/search")
    public JsonResult<List<UserBasicBean>> employeeSearch(HttpServletRequest request,@RequestParam("name")String name){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return employeeService.employeeSearch(facid,name);
        else
            return JsonResult.failMessage("权限不足");
    }
}
