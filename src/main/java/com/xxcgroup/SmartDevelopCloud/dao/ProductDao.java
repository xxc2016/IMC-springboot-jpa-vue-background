package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Equiptoproduct;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    Product findByFactoryAndProductname(Factory factory, String productname);
    boolean existsByFactoryAndProductname(Factory factory,String productname);
    List<Product> findAllByFactory(Factory factory);
    List<Product> findALLByEquips(Equiptoproduct equips);
}
