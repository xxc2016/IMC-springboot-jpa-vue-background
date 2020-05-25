package com.xxcgroup.SmartDevelopCloud.dao;

import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import com.xxcgroup.SmartDevelopCloud.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackDao extends JpaRepository<Track,Integer> {
    List<Track> findAllBySche(Sche sche);

    @Query(value = "select *" +
            " from track,sche,plan,deal " +
            "where track.scheid=sche.scheid and sche.planid=plan.planid and plan.dealid=deal.dealid and deal.facid=?1",nativeQuery = true)
    List<Track> findAllByFacid(int facid);
}
