package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.EquipProductId;
import com.xxcgroup.SmartDevelopCloud.entity.Equiptoproduct;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquiptoproductDao extends JpaRepository<Equiptoproduct, EquipProductId> {
    Equiptoproduct findAllByProduct(Product product);
    Equiptoproduct findAllByEquip(Equip equip);
    Equiptoproduct findByProductAndEquip(Product product,Equip equip);
}
