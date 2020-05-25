package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Deal;
import com.xxcgroup.SmartDevelopCloud.entity.Factory;
import com.xxcgroup.SmartDevelopCloud.entity.Plan;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DealDao extends JpaRepository<Deal,Integer> {
    List<Deal> findAllByFactory(Factory factory);
    boolean existsByProductAndState(Product product,int state);
    Integer countByState(int state);//0123
    boolean existsByDealidAndNumLessThanEqual(Integer dealid,int capacity);

    @Query("select new map(state as state, count(*) as num) from Deal where facid=?1 group by state")
    List<Map<String,Object>> findGroupByDealStates(int facid);//groupby订单状态求count：显示各状态下的订单数量
}
