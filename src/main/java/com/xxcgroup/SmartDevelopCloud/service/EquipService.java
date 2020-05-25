package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.EquipBean;
import com.xxcgroup.SmartDevelopCloud.bean.ProductBean;
import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.*;
import com.xxcgroup.SmartDevelopCloud.enumbean.EquipState;
import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.BeanUtilsHelper;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EquipService {
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private FactoryDao factoryDao;
    @Autowired
    private ScheDao scheDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private EquiptoproductDao equiptoproductDao;
    public JsonResult<List<EquipBean>> showEquip(int facid) { //查看设备
        List<Equip> equips=equipDao.findAllByFactory(factoryDao.findById(facid).orElse(null));
        List<EquipBean> beans=new ArrayList<>();
        for (Equip p:equips) {
            beans.add(new EquipBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<EquipBean> addEquip(int facid, Equip a, Map<Integer,Integer> capacity){//按钮新增设备
        if(a.getEquipno().equals(""))return JsonResult.failMessage("设备序列号不能为空");
        a.setFactory(factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid)));
        for (int k:capacity.keySet()) {
            a.addProduct(productDao.findById(k).orElseThrow(()->new EntityNotFoundException(""+k)),capacity.get(k));
        }
        if(!equipDao.existsByFactoryAndEquipno(a.getFactory(),a.getEquipno())) {
            return JsonResult.success(new EquipBean(equipDao.save(a)));
        }else{
            return JsonResult.failMessage("设备名不能相同");
        }
    }
    public JsonResult<EquipBean> removeEquip(int equipid){ //按钮删除设备
        Equip equip=equipDao.findById(equipid).orElse(null);
        if(equip==null)return JsonResult.failMessage("设备不存在");
        if(equip.getState()==EquipState.RUNNING.getCode()){
            return JsonResult.failMessage("加工中的设备不可删除");
        }
        equipDao.delete(equip);
        return JsonResult.successMessage("删除成功");

    }
    public JsonResult<EquipBean> updateEquip(int facid,Equip a,Map<Integer,Integer> capacity){ //按钮更改设备
//        if(a.getEquipno().equals(""))return JsonResult.failMessage("设备序列号不能为空");
        a.setProducts(null);
        a.setState(null);
        Factory factory=factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid));
        Equip oldequip = equipDao.findById(a.getEquipid()).orElse(null);
        if(oldequip!=null) {
            if(oldequip.getState()==EquipState.RUNNING.getCode()){
                return JsonResult.failMessage("加工中的设备不可更改");
            }
            if (!equipDao.existsByFactoryAndEquipno(factory, a.getEquipno())) {
                if(capacity!=null) {
                    for (int k : capacity.keySet()) {
                        Product product=productDao.findById(k).orElse(null);
                        if(product==null)return JsonResult.failMessage("产品不存在");
                        Equiptoproduct e=equiptoproductDao.findByProductAndEquip(product,oldequip);
                        if(e==null)
                            oldequip.getProducts().add(new Equiptoproduct(product, oldequip,capacity.get(k)));
                        else {
                            e.setNum(capacity.get(k));
                            equiptoproductDao.save(e);
                        }
                    }
                }
                BeanUtils.copyProperties(a,oldequip, BeanUtilsHelper.getNullPropertyNames(a));
                return JsonResult.success(new EquipBean(equipDao.save(oldequip)));
            } else {
                return JsonResult.failMessage("设备名不能相同");
            }
        }else{
            return JsonResult.failMessage("设备不存在");
        }
    }


    public JsonResult<EquipBean> searchEquip(int facid,String equipno){ //按钮查询设备
        Factory factory=factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid));
        Equip equip= equipDao.findByFactoryAndEquipno(factory,equipno);
        if(equip!=null)
            return JsonResult.success(new EquipBean(equip));
        else
            return JsonResult.failMessage("无此设备名");

    }

    public JsonResult<EquipBean> errorEquip(int facid, int equipid) {
        Equip equip=equipDao.findById(equipid).orElse(null);
        if(equip==null)return JsonResult.failMessage("设备不存在");
        equip.setState(EquipState.ERROR.getCode());
        return JsonResult.success(new EquipBean(equipDao.save(equip)));
    }

    public JsonResult<EquipBean> stopEquip(int facid, int equipid) {
        Equip equip=equipDao.findById(equipid).orElse(null);
        if(equip==null)return JsonResult.failMessage("设备不存在");
        equip.setState(EquipState.STOPPING.getCode());
        return JsonResult.success(new EquipBean(equipDao.save(equip)));
    }

    public JsonResult<EquipBean> startEquip(int facid, int equipid) {
        Equip equip=equipDao.findById(equipid).orElse(null);
        if(equip==null)return JsonResult.failMessage("设备不存在");
        equip.setState(EquipState.RUNNING.getCode());
        return JsonResult.success(new EquipBean(equipDao.save(equip)));
    }

    public JsonResult<EquipBean> waitEquip(int facid, int equipid) {
        Equip equip=equipDao.findById(equipid).orElse(null);
        if(equip==null)return JsonResult.failMessage("设备不存在");
        equip.setState(EquipState.WAITTING.getCode());
        return JsonResult.success(new EquipBean(equipDao.save(equip)));
    }
}
