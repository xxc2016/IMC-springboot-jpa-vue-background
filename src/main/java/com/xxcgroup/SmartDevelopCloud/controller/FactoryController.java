package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.FactoryBean;
import com.xxcgroup.SmartDevelopCloud.bean.UserDetailBean;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.FactoryService;
import com.xxcgroup.SmartDevelopCloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/factory")
public class FactoryController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private UserService userService;
    @GetMapping("/index")
    public JsonResult<List<String>> showInfo(HttpServletRequest request){
        String username=request.getSession().getAttribute("username").toString();
        return userService.factoryList(username);
    }
}
