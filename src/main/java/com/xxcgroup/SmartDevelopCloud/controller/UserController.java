package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.FactoryBean;
import com.xxcgroup.SmartDevelopCloud.bean.UserBasicBean;
import com.xxcgroup.SmartDevelopCloud.bean.UserDetailBean;
import com.xxcgroup.SmartDevelopCloud.entity.User;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonReturnCode;
import com.xxcgroup.SmartDevelopCloud.service.FactoryService;
import com.xxcgroup.SmartDevelopCloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户信息操作
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private FactoryService factoryService;
    @PostMapping("/login")
    public JsonResult<UserBasicBean> userLogin(HttpServletRequest request, @RequestParam ("username")String username, @RequestParam("password")String pwd){
        JsonResult<UserBasicBean> user=userService.userLogin(username,pwd);
        if(user.getData()!=null) {
            HttpSession session=request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("state",user.getData().getState());
            return user;
        }
        else
            return user;
    }
    @PostMapping("/register")
    public JsonResult<UserBasicBean> userRegister(HttpServletRequest request,@RequestParam("username")String username, @RequestParam("password")String pwd){
        if(username.equals(""))return JsonResult.failMessage("用户名不能为空");
        if(pwd.equals(""))return JsonResult.failMessage("密码不能为空");
        JsonResult<UserBasicBean> newUser=userService.userRegister(username,pwd);
        if(newUser.getData()!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("state", newUser.getData().getState());
            return newUser;
        }
        else
            return newUser;
    }
    @GetMapping("/info")
    public JsonResult<UserDetailBean> showInfo(HttpServletRequest request){
        String username=request.getSession().getAttribute("username").toString();
        return userService.showInfo(username);
    }
    @PostMapping("/info/update")
    public JsonResult<UserDetailBean> userUpdate(HttpServletRequest request,@RequestBody User user){
        String username=request.getSession().getAttribute("username").toString();
        user.setUsername(username);
        user.setState(null);
        return userService.userUpdate(user);
    }
    @PostMapping("/registerFactory")
    public JsonResult<FactoryBean> factoryRegister(HttpServletRequest request,@RequestParam("facname")String facname){
        String username=request.getSession().getAttribute("username").toString();
        int state= (int) request.getSession().getAttribute("state");
        if(state==UserState.NO_FAC.getCode()||state==UserState.BOSS.getCode()) {//无工厂或老板
            JsonResult<FactoryBean> bean=factoryService.facRegister(facname,username);
            if(!bean.getCode().equals(JsonReturnCode.FAIL.getCode())) {
                request.getSession().setAttribute("facid", bean.getData().getFacid());
                request.getSession().setAttribute("state", UserState.BOSS.getCode());
            }
            return bean;
        }else
            return JsonResult.failMessage("权限不足");
    }

    @PostMapping("/loginFactory")
    public JsonResult<FactoryBean> factoryLogin(HttpServletRequest request,@RequestParam("facname")String facname){
        int state= (int) request.getSession().getAttribute("state");
//        String username = request.getSession().getAttribute("username").toString();
        if(state!=UserState.NO_FAC.getCode()) {
            JsonResult<FactoryBean> bean = factoryService.facLogin(facname);
            request.getSession().setAttribute("facid", bean.getData().getFacid());
            return bean;
        }
        else
            return JsonResult.failMessage("权限不足");
    }

    @PostMapping("/joinFactory")
    public JsonResult<FactoryBean> factoryJoin(HttpServletRequest request,@RequestParam("facname")String facname){
        String username=request.getSession().getAttribute("username").toString();
        int state= (int) request.getSession().getAttribute("state");
        if(state==UserState.NO_FAC.getCode())
            return factoryService.facJoin(username,facname);
        else
            return JsonResult.failMessage("权限不足");
    }

    @GetMapping("/signout")
    public JsonResult<String> userSignout(HttpServletRequest request){
        request.getSession().invalidate();
        return JsonResult.successMessage("注销成功");
    }

//    @PostMapping("/forgetPassword")
//    public JsonResult<String> forgetPassword(@RequestParam("username")String username){
//
//    }
}
