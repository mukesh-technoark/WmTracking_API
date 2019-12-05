/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.service.DriverService;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wmtrucking.CommonResponseService;
import com.wmtrucking.dtos.CommonResponse;
import com.wmtrucking.dtos.LoginResponseDto;
import com.wmtrucking.dtos.OTPVarifyDto;
import com.wmtrucking.dtos.RequestOtpDto;
import com.wmtrucking.entity.MaDriver;
import com.wmtrucking.exceptions.InvalidHeaderException;
import com.wmtrucking.exceptions.InvalidTokenException;
import com.wmtrucking.utils.AppUtil;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.MaJWT;
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

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<Object> authentication(Model model, HttpServletRequest request, @RequestHeader(name = "devicetoken") String devicetoken,
            @RequestHeader(name = "apptoken") String apptoken, @ApiParam(value = "{\"phone\":\"phone\"}", required = true,
                    defaultValue = "hi", name = "json") @RequestBody String json) throws InvalidHeaderException {
        JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();

        appUtil.checkHeaders(request);

        MaDriver maDriver = driverService.findByPhoneAndStatus(requestData.get("phone").getAsString(), "Active");
        if (maDriver == null) {
            // commonResponseService.setResponse(response, 0, "Please Proper Mobile number ", null);
            return new ResponseEntity(new CommonResponse("Please provide Proper Mobile number", null, 0, null), HttpStatus.CREATED);
        }
        String RandomeCode = "9999";

        maDriver.setOTP(Long.parseLong(RandomeCode));
        Calendar smadate = Calendar.getInstance();
        smadate.setTime(new Date());
        smadate.add(Calendar.MINUTE, 2);

        maDriver.setOtp_expire_time(smadate.getTime());
        driverService.save(maDriver);
        return new ResponseEntity(new CommonResponse("Please verify your phone.", new LoginResponseDto(RandomeCode, new MaJWT().generateWithExpires(maDriver.getId(), 120)), 1, null), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/OTPvarification", method = RequestMethod.POST)
    public ResponseEntity<Object> OTPvarification(Model model, HttpServletRequest request, @RequestHeader("Authorization") String Authorization,
            @RequestHeader("apptoken") String apptoken,
            @RequestHeader("devicetoken") String devicetoken, @RequestBody @Valid RequestOtpDto requestOtpDto) throws InvalidHeaderException, InvalidTokenException {
        appUtil.checkHeadersWithAuth(request);
        String id = maJWT.getId(appUtil.getJwt(request), Authorization, devicetoken);

        if (id == null) {
            //  throw new InvalidTokenException("Your session is expired.");
            return new ResponseEntity(new CommonResponse("Your session is expired", null, 0, null), HttpStatus.CREATED);

        }
        Long authid = Long.valueOf(id);

        MaDriver maDriver = driverService.findByUserForOTPvarify(requestOtpDto.getOtp(), Constant.ACTIVE.toString(), authid);
        if (maDriver != null) {

            Date d1 = maDriver.getOtp_expire_time();
            Date d2 = new Date();
            // When Date d1 > Date d2 
            if (d1.compareTo(d2) > 0) {
                return new ResponseEntity(new CommonResponse("OTP is successfully varified .", new OTPVarifyDto(new MaJWT().generate(maDriver.getId())), 1, null), HttpStatus.CREATED);
            } else {
                return new ResponseEntity(new CommonResponse("Your OTP is expired ", null, 0, null), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(new CommonResponse("Please Enter proper OTP ", null, 0, null), HttpStatus.CREATED);

    }
}
