package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDao extends JpaRepository<User,String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findAllByFactories(Factory factory);
    List<User> findAllByFactoriesAndName(Factory factory,String name);
}
