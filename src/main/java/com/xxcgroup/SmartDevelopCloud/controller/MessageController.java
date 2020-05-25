package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.MessageBean;
import com.xxcgroup.SmartDevelopCloud.bean.ProductBean;
import com.xxcgroup.SmartDevelopCloud.entity.Message;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/index")
    public JsonResult<List<MessageBean>> showMessage(HttpServletRequest request) {
        int facid = (int)request.getSession().getAttribute("facid");
        return messageService.showMessage(facid);
    }
    @PostMapping("/add")
    public JsonResult<MessageBean> addMessage(HttpServletRequest request, @RequestBody Message message) {
        int facid = (int)request.getSession().getAttribute("facid");
        return messageService.addMessage(facid,message);
    }
    @GetMapping("/remove")
    public JsonResult<MessageBean> removeMessage(HttpServletRequest request,@RequestParam int messageid) {
        int state = (int)request.getSession().getAttribute("state");
        if(state==UserState.BOSS.getCode()||state==UserState.MANAGER.getCode())
            return messageService.removeMessage(messageid);
        else
            return JsonResult.failMessage("权限不足");
    }
}
