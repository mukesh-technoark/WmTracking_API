/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.dtos.JobTransactiondto;
import com.wmtrucking.entity.MaDriver;
import com.wmtrucking.entity.MaJobTransaction;
import com.wmtrucking.entity.MaJobs;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.exceptions.InvalidTokenException;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.service.DriverService;
import com.wmtrucking.service.JobService;
import com.wmtrucking.service.JobTrackingService;
import com.wmtrucking.service.JobTransactionService;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/JobDetails")
@RestController
@Scope("request")
public class JobTruckingController {

    @Autowired
    AppUtil appUtil;
    @Autowired
    MaJWT maJWT;
   
    @Autowired
    JobTrackingService jobTrackingService;
    @Autowired
    JobTransactionService jobTransactionService;
    @Autowired
    DriverService driverService;
    @Autowired
    JobService jobService;
    @RequestMapping(value = "/{jobid}", method = RequestMethod.GET)
    public ResponseEntity<Object> Job(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken, @PathVariable("jobid") Long jobid) throws InvalidHeaderException, InvalidTokenException {

        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);

        if (id == null) {
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
        }
        Long driverid = Long.valueOf(id);
        // MaJobs maJobs = jobService.findPendingJob(jobid, Constant.ACTIVE.toString(), driverid);
        //List<JobPojo> maJobs = jobService.getJob(Constant.ACTIVE.toString(),jobid, driverid);
        JobPojo maJobs = jobService.getJob(Constant.ACTIVE.toString(), jobid, driverid, new Date());
        if (maJobs != null) {
            return new ResponseEntity(new CommonResponse("Fetch job successfully ", maJobs, 1, null), HttpStatus.CREATED);
        }
        return new ResponseEntity(new CommonResponse("No Record found ", null, 0, null), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/jobtransaction", method = RequestMethod.POST)
    public ResponseEntity<Object> jobTransaction(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken,
            @RequestBody @Valid JobTransactiondto jobTransactiondto) throws InvalidHeaderException, InvalidTokenException {

        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);

        if (id == null) {
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
        }
        Long driverid = Long.valueOf(id);

        MaDriver maDriver = driverService.findById(driverid, Constant.ACTIVE.toString());

        MaJobs maJobs = jobService.findTotalJob(jobTransactiondto.getJob_id(), Constant.ACTIVE.toString(), driverid);

        if (maJobs != null && maDriver != null) {

            /**
             * Check job is started or not for this Driver. *
             */
            MaJobTransaction maJobTransaction = jobTransactionService.findTransaction(driverid, maJobs.getId(), Constant.STARTED.toString());

            /**
             * If Job is started *
             */
            if (jobTransactiondto.getFlag() != null && jobTransactiondto.getFlag().equalsIgnoreCase("start")) {

                /**
                 * One Driver start only one job at a time *
                 */
                if (maJobTransaction == null) {
                    /**
                     * transaction list must be less then job count for any
                     * driver*
                     */
                    List<MaJobTransaction> totalJob = jobTransactionService.totalAvailableJob(maJobs.getId(), maJobs.getTotaljobcount());
                    if (totalJob.size() <= 0) {
                        maJobTransaction = new MaJobTransaction();
                        maJobTransaction.setCreateddate(new Date());
                        maJobTransaction.setDriverid(maDriver);
                        maJobTransaction.setJobId(maJobs);
                        maJobTransaction.setStarttime(new Date());
                        maJobTransaction.setStatus(Constant.STARTED.toString());
                        jobTransactionService.save(maJobTransaction);
                        return new ResponseEntity(new CommonResponse("Job started successfully ", maJobTransaction, 1, null), HttpStatus.CREATED);
                    } else {
                        return new ResponseEntity(new CommonResponse("Whole job transaction has completed", null, 0, null), HttpStatus.CREATED);
                    }
                } else {
                    return new ResponseEntity(new CommonResponse("Job is already started ", null, 0, null), HttpStatus.CREATED);
                }
            } /**
             * If Job is Ended *
             */
            else if (jobTransactiondto.getFlag() != null && jobTransactiondto.getFlag().equalsIgnoreCase("end")) {
                if (maJobTransaction != null) {
                    maJobTransaction.setEndtime(new Date());
                    maJobTransaction.setStatus(Constant.ENDED.toString());
                    jobTransactionService.save(maJobTransaction);

                    /**
                     * Update job status in job table when all transaction has
                     * been completed *
                     */
                    Long totalTransation = jobTransactionService.totalJobTransactionCount(maJobs.getId());
                    if (totalTransation == maJobs.getTotaljobcount()) {
                        maJobs.setJobStatus(Constant.COMPLETED.toString());
                        jobService.save(maJobs);
                    }
                    return new ResponseEntity(new CommonResponse("Job ended successfully", maJobTransaction, 1, null), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity(new CommonResponse("First we need to start the job ", null, 0, null), HttpStatus.CREATED);
                }
            }
        }
        return new ResponseEntity(new CommonResponse("Something went wrong ", null, 0, null), HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/jobLocationUpdate", method = RequestMethod.POST)
//    public ResponseEntity<Object> jobLocationUpdate(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
//            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken,
//            @RequestBody @Valid JobTruckingDto jobTruckingDto) throws InvalidHeaderException, InvalidTokenException {
//
//        appUtil.checkHeadersWithAuth(request);
//        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);
//
//        if (id == null) {
//            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
//        }
//        Long driverid = Long.valueOf(id);
//
//        MaDriver maDriver = driverService.findById(driverid, Constant.ACTIVE.toString());
//        MaJobs maJobs = jobService.findPendingJob(jobTruckingDto.getJob_id(), Constant.ACTIVE.toString(), driverid);
//        if (maJobs != null && maDriver != null) {
//            MaJobTracking maJobTracking = new MaJobTracking();
//            maJobTracking.setCreateddate(new Date());
//            maJobTracking.setDriverId(maDriver);
//            maJobTracking.setJobId(maJobs);
//            maJobTracking.setLongitude(jobTruckingDto.getLongitude());
//            maJobTracking.setLatitude(jobTruckingDto.getLatitude());
//            jobTrackingService.save(maJobTracking);
//        }
//        return new ResponseEntity(new CommonResponse("No Record found ", null, 0, null), HttpStatus.CREATED);
//    }
}
