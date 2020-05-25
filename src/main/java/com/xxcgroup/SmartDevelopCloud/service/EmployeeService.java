package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.UserBasicBean;
import com.xxcgroup.SmartDevelopCloud.dao.FactoryDao;
import com.xxcgroup.SmartDevelopCloud.dao.UserDao;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.User;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private FactoryDao factoryDao;
    public JsonResult<List<UserBasicBean>> employeeInfo(int facid){
        Factory factory=factoryDao.findById(facid).orElse(null);
        if(factory==null)return JsonResult.failMessage("未登录工厂");
        List<UserBasicBean> beans=new ArrayList<>();
        for (User user:userDao.findAllByFactories(factory)) {
            beans.add(new UserBasicBean(user));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<UserBasicBean> employeeRemove(int facid,String username){
        Factory factory=factoryDao.findById(facid).orElse(null);
        if(factory==null)return JsonResult.failMessage("未登录工厂");
        User user=userDao.findByUsername(username);
        if(user!=null){
            user.getFactories().clear();
            user.setState(UserState.NO_FAC.getCode());
            userDao.save(user);
            return JsonResult.successMessage("删除成功");
        }else{
            return JsonResult.failMessage("用户不存在");
        }
    }
    public JsonResult<UserBasicBean> employeeAdd(int facid,String username){
        Factory factory=factoryDao.findById(facid).orElse(null);
        if(factory==null)return JsonResult.failMessage("未登录工厂");
        User user=userDao.findByUsername(username);
        if(user!=null){
            user.setState(UserState.EMPLOYEE.getCode());
            userDao.save(user);
            return JsonResult.success(new UserBasicBean(user));
        }else
            return JsonResult.failMessage("用户不存在");

    }
    public JsonResult<UserBasicBean> employeePromote(int facid,String username){
        Factory factory=factoryDao.findById(facid).orElse(null);
        if(factory==null)return JsonResult.failMessage("未登录工厂");
        User user=userDao.findByUsername(username);
        if(user!=null){
            user.setState(UserState.MANAGER.getCode());
            userDao.save(user);
            return JsonResult.success(new UserBasicBean(user));
        }else
            return JsonResult.failMessage("用户不存在");
    }
    public JsonResult<List<UserBasicBean>> employeeSearch(int facid, String name){
        Factory factory=factoryDao.findById(facid).orElse(null);
        if(factory==null)return JsonResult.failMessage("未登录工厂");
        List<User> users=userDao.findAllByFactoriesAndName(factory,name);
        if(users!=null){
            List<UserBasicBean> beans=new ArrayList<>();
            for (User user:users) {
                beans.add(new UserBasicBean(user));
            }
            return JsonResult.success(beans);
        }else
            return JsonResult.failMessage("用户不存在");
    }
}
