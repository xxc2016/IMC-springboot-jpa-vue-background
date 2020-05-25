package com.xxcgroup.SmartDevelopCloud.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xxcgroup.SmartDevelopCloud.bean.ProductBean;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.FactoryService;
import com.xxcgroup.SmartDevelopCloud.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ProductService productService;
    @GetMapping("/index")
    public JsonResult<List<ProductBean>> showProduct(HttpServletRequest request) {
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return productService.showProduct(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/add")
    public  JsonResult<ProductBean> addProduct(HttpServletRequest request, @RequestBody Product product){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode()){
            return productService.addProduct(facid,product);
        }
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/remove")
    public  JsonResult<ProductBean> removeProduct(HttpServletRequest request,@RequestParam("productid")int productid){
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return productService.removeProduct(productid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/update")
    public  JsonResult<ProductBean> updateProduct(HttpServletRequest request,@RequestBody Product product){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state== UserState.BOSS.getCode())
            return productService.updateProduct(facid,product);
        else
            return JsonResult.failMessage("权限不足");
    }
    @PostMapping("/search")
    public JsonResult<ProductBean> searchProduct(HttpServletRequest request,@RequestParam("productname")String productname){
        int facid= (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state!= UserState.NO_FAC.getCode())
            return productService.searchProduct(facid,productname);
        else
            return JsonResult.failMessage("权限不足");
    }

}
