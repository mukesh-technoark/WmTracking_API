/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.service.DriverService;
import javax.servlet.http.HttpServletRequest;
import com.wmtrucking.CommonResponseService;
import com.wmtrucking.dtos.Authenticationdto;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.dtos.OTPVarifyDto;
import com.wmtrucking.entity.MaDriver;
import com.wmtrucking.entity.MaPushNotification;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.service.PushNotifcationService;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author Admin
 */
@RequestMapping("/Authentication")
@RestController
@Scope("request")
public class AuthenticationController {

    @Autowired
    DriverService driverService;
    @Autowired
    CommonResponseService commonResponseService;
    @Autowired
    AppUtil appUtil;
    @Autowired
    MaJWT maJWT;
    @Autowired
    PushNotifcationService pushNotifcationService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<Object> authentication(Model model, HttpServletRequest request, @RequestHeader(name = "devicetoken") String devicetoken,
            @RequestHeader(name = "devicetype") String devicetype,
            @RequestHeader(name = "apptoken") String apptoken, @RequestBody @Valid Authenticationdto authenticationdto) throws InvalidHeaderException {

        //JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();
        appUtil.checkHeaders(request);

        //     MaDriver maDriver = driverService.findByPhoneAndStatus(authenticationdto.getPhone(), "Active", authenticationdto.getCountryCode());
//        if (maDriver == null) {
//            return new ResponseEntity(new CommonResponse("Please provide proper mobile number", null, 0, null), HttpStatus.CREATED);
//        }
        MaDriver maDriver = driverService.findByEmailAndStatus(authenticationdto.getEmail(), Constant.ACTIVE.toString(), authenticationdto.getPassword(), authenticationdto.getEmail());
        if (maDriver == null) {
            return new ResponseEntity(new CommonResponse("Please provide proper value", null, 0, null), HttpStatus.CREATED);
        }

        String RandomeCode = "9999";
        maDriver.setOtp(Long.parseLong(RandomeCode));
        Calendar smadate = Calendar.getInstance();
        smadate.setTime(new Date());
        smadate.add(Calendar.MINUTE, 2);

        maDriver.setOtpExpireTime(smadate.getTime());
        driverService.save(maDriver);

        MaPushNotification pushNotification = pushNotifcationService.findAlreadyExist(maDriver.getId(), Constant.ACTIVE.toString(), devicetoken);
        if (pushNotification != null) {
            pushNotification.setDatecreated(new Date());
            pushNotification.setDevicetoken(devicetoken);
        } else {
            pushNotification = new MaPushNotification();
            pushNotification.setDatecreated(new Date());
            pushNotification.setStatus(Constant.ACTIVE.toString());
            pushNotification.setDriverid(maDriver);
            pushNotification.setDevicetoken(devicetoken);
            pushNotification.setType(request.getHeader("DeviceType") == null ? null : devicetype);
        }
        pushNotifcationService.save(pushNotification);

        //return new ResponseEntity(new CommonResponse("Please verified your phone.", new LoginResponseDto(RandomeCode, new MaJWT().generateWithExpires(maDriver.getId(), 120)), 1, null), HttpStatus.CREATED);
        return new ResponseEntity(new CommonResponse("You are login successfully.", new OTPVarifyDto(new MaJWT().generate(maDriver.getId()), maDriver.getId(), maDriver.getFirstname(), maDriver.getMiddlename(), maDriver.getLastname()), 1, null), HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/OTPvarification", method = RequestMethod.POST)
//    public ResponseEntity<Object> OTPvarification(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
//            @RequestHeader("apptoken") String apptoken,
//            @RequestHeader("devicetoken") String devicetoken, @RequestBody @Valid RequestOtpDto requestOtpDto) throws InvalidHeaderException, InvalidTokenException {
//        appUtil.checkHeadersWithAuth(request);
//        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);
//
//        if (id == null) {
//            //  throw new InvalidTokenException("Your session is expired.");
//            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);
//        }
//        Long authid = Long.valueOf(id);
//
//        MaDriver maDriver = driverService.findByUserForOTPvarify(requestOtpDto.getOtp(), Constant.ACTIVE.toString(), authid);
//        if (maDriver != null) {
//
//            Date d1 = maDriver.getOtpExpireTime();
//            Date d2 = new Date();
//            // When Date d1 > Date d2 
//            if (d1.compareTo(d2) > 0) {
//                // return new ResponseEntity(new CommonResponse("OTP is successfully verified .", new OTPVarifyDto(new MaJWT().generate(maDriver.getId())), 1, null), HttpStatus.CREATED);
//                return new ResponseEntity(new CommonResponse("OTP is successfully verified .", new OTPVarifyDto(new MaJWT().generate(maDriver.getId()), maDriver.getId(), maDriver.getFirstname(), maDriver.getMiddlename(), maDriver.getLastname()), 1, null), HttpStatus.CREATED);
//            } else {
//                return new ResponseEntity(new CommonResponse("Your OTP is expired ", null, 0, null), HttpStatus.CREATED);
//            }
//        }
//        return new ResponseEntity(new CommonResponse("Please enter proper OTP ", null, 0, null), HttpStatus.CREATED);
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
