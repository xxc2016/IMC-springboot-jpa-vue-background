package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Map;

public interface FactoryDao extends JpaRepository<Factory,Integer> {
    Factory findByFacname(String facname);//工厂的全部信息（不包括关系）
    List<Factory> findAllByUsers(User user);
    boolean existsByFacname(String facname);

}
