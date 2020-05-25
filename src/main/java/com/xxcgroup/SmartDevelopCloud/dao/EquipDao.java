package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Equip;
import com.xxcgroup.SmartDevelopCloud.entity.Equiptoproduct;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EquipDao extends JpaRepository<Equip,Integer> {
    Equip findByFactoryAndEquipno(Factory factory,String equipno);
    boolean existsByFactoryAndEquipno(Factory factory,String equipno);
    List<Equip> findAllByFactory(Factory factory);
    List<Equip> findAllByProducts(Equiptoproduct equiptoproduct);
    Integer countByState(int state);//0123

    @Query("select new map(state as state, count(*) as num) from Equip where facid=?1 group by state")
    List<Map<String,Object>> findGroupByEquipStates(int facid);//groupby设备状态求count：加工中、待机中、停机中或故障的设备数量
}
