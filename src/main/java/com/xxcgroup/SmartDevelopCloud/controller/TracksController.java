package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.bean.ScheBean;
import com.xxcgroup.SmartDevelopCloud.bean.TrackBean;
import com.xxcgroup.SmartDevelopCloud.entity.Product;
import com.xxcgroup.SmartDevelopCloud.entity.Track;
import com.xxcgroup.SmartDevelopCloud.enumbean.UserState;
import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import com.xxcgroup.SmartDevelopCloud.service.ScheService;
import com.xxcgroup.SmartDevelopCloud.service.TracksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TracksController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private TracksService trackService;
    @Autowired
    private ScheService scheService;
    @PostMapping("/report")
    public JsonResult<TrackBean> report(HttpServletRequest request,@RequestBody Track track){
        int state= (int) request.getSession().getAttribute("state");
        if(state != UserState.NO_FAC.getCode())
            return trackService.report(track);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/finishSche")
    public JsonResult<TrackBean> finishSche(HttpServletRequest request,@RequestParam("trackid")Integer trackid){
        int state= (int) request.getSession().getAttribute("state");
        if(state != UserState.NO_FAC.getCode())
            return trackService.finishSche(trackid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/scheIndex")
    public JsonResult<List<ScheBean>> showStartedScheList(HttpServletRequest request){
        int facid = (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state != UserState.NO_FAC.getCode())
            return trackService.showStartedScheList(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/trackIndex")
    public JsonResult<List<TrackBean>> showTrackList(HttpServletRequest request){
        int facid = (int) request.getSession().getAttribute("facid");
        int state= (int) request.getSession().getAttribute("state");
        if(state != UserState.NO_FAC.getCode())
            return trackService.showTrackList(facid);
        else
            return JsonResult.failMessage("权限不足");
    }
    @GetMapping("/search")
    public JsonResult<List<TrackBean>> searchTrackList(HttpServletRequest request,@RequestParam("scheid")Integer scheid){
        int state= (int) request.getSession().getAttribute("state");
        if(state != UserState.NO_FAC.getCode())
            return trackService.searchTrackList(scheid);
        else
            return JsonResult.failMessage("权限不足");
    }
}
