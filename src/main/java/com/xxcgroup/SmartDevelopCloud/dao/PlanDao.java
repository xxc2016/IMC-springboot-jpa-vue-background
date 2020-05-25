package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanDao extends JpaRepository<Plan,Integer> {
    List<Plan> findAllByDeal(Deal deal);


    @Query(value = "select * " +
            "from plan,deal " +
            "where plan.dealid=deal.dealid and deal.facid=?1",nativeQuery = true)
    List<Plan> findAllByFacid(int facid);
}
