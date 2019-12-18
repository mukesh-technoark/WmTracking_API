/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.CommonResponseService;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.entity.MaJobs;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.exceptions.InvalidTokenException;
import com.wmtrucking.service.DriverService;
import com.wmtrucking.service.JobService;
import com.wmtrucking.service.JobTransactionService;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RequestMapping("/job")
@RestController
@Scope("request")
public class JobController {

    @Autowired
    CommonResponseService commonResponseService;
    @Autowired
    AppUtil appUtil;
    @Autowired
    JobService jobService;
    @Autowired
    DriverService driverService;
    @Autowired
    MaJWT maJWT;
    @Autowired
    JobTransactionService jobTransactionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Object> list(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken) throws InvalidHeaderException, InvalidTokenException {

        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);

        if (id == null) {
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
        }

        Long driverid = Long.valueOf(id);
        List<MaJobs> maJobs = jobService.findListOfJob(Constant.ACTIVE.toString(), driverid);

        if (maJobs != null && maJobs.size() > 0) {
            return new ResponseEntity(new CommonResponse("Fetch list of job successfully ", maJobs, 1, null), HttpStatus.CREATED);
        }
        return new ResponseEntity(new CommonResponse("No record found ", null, 1, null), HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/jobUpdate", method = RequestMethod.POST)
//    public ResponseEntity<Object> accept(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
//            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken, @RequestBody String json) throws InvalidHeaderException, InvalidTokenException {
//        JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();
//
//        appUtil.checkHeadersWithAuth(request);
//        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);
//        if (id == null) {
//            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
//
//        }
//        if (!requestData.has("status")) {
//            return new ResponseEntity(new CommonResponse("Please provide status ", null, 0, null), HttpStatus.CREATED);
//        }
//        if (requestData.get("status").isJsonNull()) {
//            return new ResponseEntity(new CommonResponse("Please provide status ", null, 0, null), HttpStatus.CREATED);
//        }
//        if (!requestData.has("jobid")) {
//            return new ResponseEntity(new CommonResponse("Please provide jobid ", null, 0, null), HttpStatus.CREATED);
//        }
//        if (requestData.get("jobid").isJsonNull()) {
//            return new ResponseEntity(new CommonResponse("Please provide jobid ", null, 0, null), HttpStatus.CREATED);
//        }
//
//        String jobstatus = requestData.get("status").getAsString();
//       
//        Long driverid = Long.valueOf(id);
//           System.out.println("jobstatus.0011."+ jobstatus);
//        MaJobs maJobs = jobService.findPendingJob(Long.parseLong(requestData.get("jobid").getAsString()), Constant.ACTIVE.toString(), driverid);
//          System.out.println("jobstatus.11."+ jobstatus);
//        if (maJobs != null) {
//            String status = "";
//            MaDriver maDriver = driverService.findById(driverid, Constant.ACTIVE.toString());
//            maJobs.setDriverid(maDriver);
//             System.out.println("jobstatus.."+ jobstatus);
//            if (jobstatus.equalsIgnoreCase(Constant.ACCEPTED.toString())) {
//                maJobs.setJobStatus(Constant.ACCEPTED.toString());
//                status = Constant.ACCEPTED.toString();
//            } else if (jobstatus.equalsIgnoreCase(Constant.CLOSE.toString())) {
//                maJobs.setJobStatus(Constant.CLOSE.toString());
//                status = Constant.CLOSE.toString();
//            }
//            jobService.save(maJobs);
//            return new ResponseEntity(new CommonResponse(status, maJobs, 1, null), HttpStatus.CREATED);
//        }
//        return new ResponseEntity(new CommonResponse("Something went wrong ", null, 0, null), HttpStatus.CREATED);
//
//    }
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        JsonObject response = new JsonObject();
        ex.printStackTrace();
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));

        commonResponseService.setResponse(response, 0, "Some error has occured", null);

        return response.toString();
    }
}
