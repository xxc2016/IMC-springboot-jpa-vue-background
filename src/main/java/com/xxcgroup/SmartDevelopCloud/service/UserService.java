package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.UserBasicBean;
import com.xxcgroup.SmartDevelopCloud.bean.UserDetailBean;
import com.xxcgroup.SmartDevelopCloud.dao.FactoryDao;
import com.xxcgroup.SmartDevelopCloud.dao.UserDao;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.User;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.BeanUtilsHelper;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private FactoryDao factoryDao;
    public JsonResult<UserBasicBean> userLogin(String username,String password){
        User user=userDao.findByUsername(username);
        if(user!=null&&user.getPassword().equals(password)) {
            UserBasicBean bean = new UserBasicBean(user);
            List<Factory> factories=factoryDao.findAllByUsers(user);
            for (Factory f:factories) {
                bean.getFacid().add(f.getFacid());
                bean.getFacname().add(f.getFacname());
            }
            return JsonResult.success(bean);
        }
        else
            return JsonResult.failMessage("用户不存在或密码错误");
    }

    public JsonResult<UserBasicBean> userRegister(String username,String password){
        if(!userDao.existsByUsername(username)) {
            User newUser = new User(username, password);
            userDao.save(newUser);
            return JsonResult.success(new UserBasicBean(newUser));
        }
        else
            return JsonResult.failMessage("用户已存在");
    }

    public JsonResult<UserDetailBean> showInfo(String username){
        return JsonResult.success(new UserDetailBean(userDao.findByUsername(username)));
    }

    public JsonResult<UserDetailBean> userUpdate(User a){
        a.setFactories(null);
        if(userDao.existsByUsername(a.getUsername())) {
            User user=userDao.findByUsername(a.getUsername());
            BeanUtils.copyProperties(a,user, BeanUtilsHelper.getNullPropertyNames(a));
            userDao.save(user);
            return JsonResult.success(new UserDetailBean(user));
        }
        else
            return JsonResult.failMessage("更新失败");
    }
    public JsonResult<List<String>> factoryList(String username){
        List<String> list=new ArrayList<>();
        User user=userDao.findByUsername(username);
        List<Factory> factories=factoryDao.findAllByUsers(user);
        for (Factory f:factories) {
            list.add(f.getFacname());
        }
        return JsonResult.success(list);
    }
}
