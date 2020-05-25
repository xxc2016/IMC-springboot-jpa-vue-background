package com.xxcgroup.SmartDevelopCloud;

import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
class SmartDevelopCloudApplicationTests {
//	private static final Logger log = LoggerFactory.getLogger(SmartDevelopCloudApplicationTests.class);
//	@Autowired
//	public FactoryDao facDao;
//	@Autowired
//	public UserDao userDao;
//	@Autowired
//	public ProductDao productDao;
//	@Autowired
//	public EquiptoproductDao dao;
//	@Autowired
//	public EquipDao equipDao;
//	@Test
//	@Transactional
//	public void testFindALL(){
////		Equip equip=new Equip(facDao.findByFacname("测试工厂"),"x10086");
//		Product product=new Product("测试产品");
//		product.setFactory(facDao.findByFacname("测试工厂"));
//		Equip equip=equipDao.findByFactoryAndEquipno(facDao.findByFacname("测试工厂"),"x10086");
//		equip.addProduct(product,100);
//		equipDao.save(equip);
//
//		List<Equip> fac=equipDao.findAllByProducts(dao.findByEquip(equip));
////		List<User> fac=userDao.findAllByFactories(facDao.findByFacname("测试工厂"));
//		log.info(fac.get(0).getEquipno());
////		Assert.assertEquals("xxc",user.getPassword());
////		Assert.assertEquals(2,all.getTotalPages());
//	}

}
