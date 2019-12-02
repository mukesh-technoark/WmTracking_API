/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.CommonResponseService;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.service.JobService;
import com.wmtrucking.utils.AppUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
    
//     @RequestMapping(value = "/auth", method = RequestMethod.POST)
//    public ResponseEntity<Object> authentication(Model model, HttpServletRequest request,  @RequestHeader(name = "devicetoken") String devicetoken,
//            @RequestHeader(name = "apptoken") String apptoken,defaultValue = "hi", name = "json") @RequestBody String json) throws InvalidHeaderException {
//    
//        JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();
//
//        appUtil.checkHeaders(request);
//
//        MaDriver maDriver = driverService.findByPhoneAndStatus(requestData.get("phone").getAsString(), "Active");
//        if (maDriver == null) {
//            // commonResponseService.setResponse(response, 0, "Please Proper Mobile number ", null);
//            return new ResponseEntity(new CommonResponse("Please provide Proper Mobile number", null, 0, null), HttpStatus.CREATED);
//        }
//        String RandomeCode = "9999";
//
//        maDriver.setOTP(Long.parseLong(RandomeCode));
//        Calendar smadate = Calendar.getInstance();
//        smadate.setTime(new Date());
//        smadate.add(Calendar.MINUTE, 2);
//
//        maDriver.setOtp_expire_time(smadate.getTime());
//        driverService.save(maDriver);
//        return new ResponseEntity(new CommonResponse("Please verify your phone.", new LoginResponseDto(RandomeCode, new MaJWT().generateWithExpires(maDriver.getId(), 120)), 1, null), HttpStatus.CREATED);
//
//    }
}
