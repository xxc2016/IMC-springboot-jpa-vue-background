package com.xxcgroup.SmartDevelopCloud.service;

import com.xxcgroup.SmartDevelopCloud.bean.ScheBean;
import com.xxcgroup.SmartDevelopCloud.bean.TrackBean;
import com.xxcgroup.SmartDevelopCloud.dao.*;
import com.xxcgroup.SmartDevelopCloud.entity.Sche;
import com.xxcgroup.SmartDevelopCloud.entity.Track;
import com.xxcgroup.SmartDevelopCloud.enumbean.EquipState;
import com.xxcgroup.SmartDevelopCloud.enumbean.PlanState;
import com.xxcgroup.SmartDevelopCloud.enumbean.ScheState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TracksService {
    @Autowired
    private TrackDao trackDao;
    @Autowired
    private ScheDao scheDao;
    @Autowired
    private PlanDao planDao;
    public JsonResult<TrackBean> report(Track track){          //报工
        Sche sche=scheDao.findById(track.getSche().getScheid()).orElse(null);
        if(sche==null)return JsonResult.failMessage("调度id不存在");
        track.setSche(sche);
        if (sche.getState() == ScheState.DOING.getCode()){
            Track newTrack=trackDao.save(track);
            if(newTrack.getFinish()==1)
                finishSche(newTrack.getTrackid());
            return JsonResult.success(new TrackBean(newTrack));            //
        }else{
            return JsonResult.failMessage("工单未处于启动状态，不可报工");
        }
    }
    public JsonResult<TrackBean> finishSche(int trackid){          //完成报工
        Track track = trackDao.findById(trackid).orElse(null);
        if(track==null)return JsonResult.failMessage("跟踪不存在");
        track.setFinish(1);
        track.getSche().setState(ScheState.DONE.getCode());
        track.getSche().getEquip().setState(EquipState.WAITTING.getCode());
        for (Sche s:scheDao.findAllByPlan(track.getSche().getPlan())) {
            if(s.getState() != ScheState.DONE.getCode())
                return JsonResult.success(new TrackBean(trackDao.save(track)));
        }
        track.getSche().getPlan().setState(PlanState.FINISHED.getCode());
        track.getSche().getPlan().getDeal().setFinishnum(track.getOknum());
        return JsonResult.success(new TrackBean(trackDao.save(track)));   //
    }
    public JsonResult<List<ScheBean>> showStartedScheList(int facid) {         //查看已启动工单
        List<Sche> sches = scheDao.findAllByFacidAndStartedState(facid);
        List<ScheBean> beans = new ArrayList<>();
        for (Sche p:sches) {
            beans.add(new ScheBean(p));
        }
        return JsonResult.success(beans);     //
    }
    public JsonResult<List<TrackBean>> showTrackList(int facid) {         //查看跟踪
        List<Track> tracks = trackDao.findAllByFacid(facid);
        List<TrackBean> beans = new ArrayList<>();
        for (Track p:tracks) {
            beans.add(new TrackBean(p));
        }
        return JsonResult.success(beans);     //
    }

    public JsonResult<List<TrackBean>> searchTrackList(int scheid) {
        Sche sche=scheDao.findById(scheid).orElse(null);
        if(sche==null)return JsonResult.failMessage("调度不存在");
        List<Track> tracks = trackDao.findAllBySche(sche);
        List<TrackBean> beans = new ArrayList<>();
        for (Track p:tracks) {
            beans.add(new TrackBean(p));
        }
        return JsonResult.success(beans);
    }
}
