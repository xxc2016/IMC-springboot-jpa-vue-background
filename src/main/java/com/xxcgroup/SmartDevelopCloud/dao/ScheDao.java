package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ScheDao extends JpaRepository<Sche, Integer> {
    List<Sche> findAllByPlan(Plan plan);
    List<Sche> findAllByEquip(Equip equip);
    boolean existsByEquip(Equip equip);

    @Query(value = "select *" +
            " from sche,plan,deal " +
            "where sche.planid=plan.planid and plan.dealid=deal.dealid and deal.facid=?1",nativeQuery = true)
    List<Sche> findAllByFacid(int facid);//查看全部调度

    @Query(value = "select *" +
            " from sche,plan,deal " +
            "where sche.planid=plan.planid and plan.dealid=deal.dealid and deal.facid=?1 and sche.state=1",nativeQuery = true)
    List<Sche> findAllByFacidAndStartedState(int facid);//查看已启动调度

    boolean existsByEquipAndState(Equip equip, int state);
}
