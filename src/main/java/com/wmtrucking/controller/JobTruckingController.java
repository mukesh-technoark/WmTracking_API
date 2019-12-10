/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.dtos.JobTruckingDto;
import com.wmtrucking.dtos.RequestOtpDto;
import com.wmtrucking.entity.MaDriver;
import com.wmtrucking.entity.MaJobTracking;
import com.wmtrucking.entity.MaJobs;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.exceptions.InvalidTokenException;
import com.wmtrucking.service.DriverService;
import com.wmtrucking.service.JobService;
import com.wmtrucking.service.JobTrackingService;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RequestMapping("/JobTrack")
@RestController
@Scope("request")
public class JobTruckingController {
    
    @Autowired
    AppUtil appUtil;
    @Autowired
    MaJWT maJWT;
    @Autowired
    JobService jobService;
    @Autowired
    JobTrackingService jobTrackingService;
    @Autowired
    DriverService driverService;
    
    @RequestMapping(value = "/{jobid}", method = RequestMethod.GET)
    public ResponseEntity<Object> Job(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken, @PathVariable("jobid") Long jobid) throws InvalidHeaderException, InvalidTokenException {
        
        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);
        
        if (id == null) {
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
        }
        Long driverid = Long.valueOf(id);
        MaJobs maJobs = jobService.findPendingJob(jobid, Constant.ACTIVE.toString(), driverid);
        if (maJobs != null) {
            return new ResponseEntity(new CommonResponse("Fetch job sucessfully ", maJobs, 1, null), HttpStatus.CREATED);
        }
        return new ResponseEntity(new CommonResponse("No Record found ", null, 0, null), HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/jobLocationUpdate", method = RequestMethod.POST)
    public ResponseEntity<Object> jobLocationUpdate(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken, @RequestBody String json,
            @RequestBody @Valid JobTruckingDto jobTruckingDto) throws InvalidHeaderException, InvalidTokenException {
        
     //   JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();
        
        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);
        
        if (id == null) {
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
        }
        Long driverid = Long.valueOf(id);           
        
        MaDriver maDriver = driverService.findById(driverid, Constant.ACTIVE.toString());
        MaJobs maJobs = jobService.findPendingJob(jobTruckingDto.getJob_id(), Constant.ACTIVE.toString(), driverid);
        if (maJobs != null && maDriver != null) {
            MaJobTracking maJobTracking = new MaJobTracking();
            maJobTracking.setCreateddate(new Date());
            maJobTracking.setDriverId(maDriver);
            maJobTracking.setJobId(maJobs);
            maJobTracking.setLongitude(jobTruckingDto.getLongitude());
            maJobTracking.setLatitude(jobTruckingDto.getLatitude());
            jobTrackingService.save(maJobTracking);
        }
        
        return new ResponseEntity(new CommonResponse("No Record found ", null, 0, null), HttpStatus.CREATED);
        
    }
}
