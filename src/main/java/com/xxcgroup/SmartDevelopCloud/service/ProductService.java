package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.ProductBean;
import com.xxcgroup.SmartDevelopCloud.dao.DealDao;
import com.xxcgroup.SmartDevelopCloud.dao.FactoryDao;
import com.xxcgroup.SmartDevelopCloud.dao.ProductDao;
import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.enumbean.DealState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.BeanUtilsHelper;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private FactoryDao factoryDao;
    @Autowired
    private DealDao dealDao;
    public JsonResult<List<ProductBean>> showProduct(int facid) { //查看产品
        List<Product> products=productDao.findAllByFactory(factoryDao.findById(facid).orElse(null));
        List<ProductBean> beans=new ArrayList<>();
        for (Product p:products) {
            beans.add(new ProductBean(p));
        }
        return JsonResult.success(beans);
    }
    public JsonResult<ProductBean> addProduct(int facid, Product a){//按钮新增产品
        if(a.getProductname().equals(""))return JsonResult.failMessage("产品名不能为空");
        a.setFactory(factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid)));
        if(!productDao.existsByFactoryAndProductname(a.getFactory(),a.getProductname())) {
            return JsonResult.success(new ProductBean(productDao.save(a)));
        }else{
            return JsonResult.failMessage("产品名不能相同");
        }
    }
    public JsonResult<ProductBean> removeProduct(int productid){ //按钮删除产品
        Product product=productDao.findById(productid).orElse(null);
        if(product==null)return JsonResult.failMessage("产品不存在");
        if(dealDao.existsByProductAndState(product, DealState.ACCEPTED.getCode())||dealDao.existsByProductAndState(product,DealState.DOING.getCode())){
            return JsonResult.failMessage("存在关联已接单订单时不可删除");
        }
        if (productDao.existsById(productid)) {
            productDao.delete(product);
            return JsonResult.successMessage("删除成功");
        }
        else
            return JsonResult.failMessage("删除失败");

    }
    public JsonResult<ProductBean> updateProduct(int facid,Product a){ //按钮更改产品
        if(a.getProductname().equals(""))return JsonResult.failMessage("产品名不能为空");
        a.setEquips(null);
        Factory factory=factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid));
        if(productDao.existsById(a.getProductid())) {
            Product product = productDao.findById(a.getProductid()).orElseThrow(() -> new EntityNotFoundException("" + a.getProductid()));
            if (dealDao.existsByProductAndState(product, DealState.ACCEPTED.getCode()) || dealDao.existsByProductAndState(product, DealState.DOING.getCode())) {
                return JsonResult.failMessage("存在关联已接单订单时不可更改");
            }
            if (!productDao.existsByFactoryAndProductname(factory, a.getProductname())) {
                BeanUtils.copyProperties(a,product,BeanUtilsHelper.getNullPropertyNames(a));
                return JsonResult.success(new ProductBean(productDao.save(product)));
            } else {
                return JsonResult.failMessage("产品名不能相同");
            }
        }else{
            return JsonResult.failMessage("产品不存在");
        }
    }


    public JsonResult<ProductBean> searchProduct(int facid,String productname){ //按钮查询产品
        Factory factory=factoryDao.findById(facid).orElseThrow(()->new EntityNotFoundException(""+facid));
        Product product= productDao.findByFactoryAndProductname(factory,productname);
        if(product!=null)
            return JsonResult.success(new ProductBean(product));
        else
            return JsonResult.failMessage("无此产品名");

    }
}
