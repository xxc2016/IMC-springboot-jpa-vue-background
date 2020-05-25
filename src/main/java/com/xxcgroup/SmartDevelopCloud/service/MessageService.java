package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.MessageBean;
import com.xxcgroup.SmartDevelopCloud.bean.MessageBean;
import com.xxcgroup.SmartDevelopCloud.dao.FactoryDao;
import com.xxcgroup.SmartDevelopCloud.dao.MessageDao;
import com.xxcgroup.SmartDevelopCloud.dao.UserDao;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.Message;
import com.xxcgroup.SmartDevelopCloud.entity.Message;
import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private FactoryDao factoryDao;

    public JsonResult<List<MessageBean>> showMessage(int facid) {
        List<Message> messages=messageDao.findAllByFactory(factoryDao.findById(facid).orElse(null));
        List<MessageBean> beans=new ArrayList<>();
        for (Message p:messages) {
            beans.add(new MessageBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<MessageBean> addMessage(int facid, Message a){//按钮新增设备
        if(a.getContent().equals(""))return JsonResult.failMessage("提醒内容不能为空");
        a.setFactory(factoryDao.findById(facid).orElse(null));
        return JsonResult.success(new MessageBean(messageDao.save(a)));

    }
    public JsonResult<MessageBean> removeMessage(int messageid){ //按钮删除设备
        Message message=messageDao.findById(messageid).orElse(null);
        if(message==null)
            return JsonResult.failMessage("消息不存在");
        messageDao.delete(message);
        return JsonResult.successMessage("删除成功");

    }
}
