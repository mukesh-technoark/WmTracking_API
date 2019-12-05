/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.CommonResponseService;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.entity.MaJobs;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.exceptions.InvalidTokenException;
import com.wmtrucking.service.JobService;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    MaJWT maJWT;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Object> list(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken, @RequestHeader("devicetoken") String devicetoken) throws InvalidHeaderException, InvalidTokenException {

        //  JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();
        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);

        if (id == null) {
            // throw new InvalidTokenException("Your session is expired.");
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);

        }
        Long driverid = Long.valueOf(id);
        List<MaJobs> maJobs = jobService.findListOfJob(driverid, Constant.ACTIVE.toString());

        return new ResponseEntity(new CommonResponse("Fetch list of job sucessfully ", maJobs, 1, null), HttpStatus.CREATED);

    }
}
