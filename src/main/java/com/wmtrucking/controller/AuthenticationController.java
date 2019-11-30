/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.service.CustomerService;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wmtrucking.CommonResponseService;
import com.wmtrucking.entity.MaCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RequestMapping("/Authentication")
@RestController
@Scope("request")
public class AuthenticationController {

    @Autowired
    CustomerService customerService;
    @Autowired
    CommonResponseService commonResponseService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String dashboard(Model model, HttpServletRequest request, @ApiParam(value = "{\"phone\":\"phone\"}", required = true,
            defaultValue = "hi", name = "json") @RequestBody String json) {
        JsonObject requestData = new JsonParser().parse(json).getAsJsonObject();
        JsonObject response = new JsonObject();
        JsonObject responseData = new JsonObject();

        MaCustomer maCustomer = customerService.findByPhoneAndStatus(requestData.get("phone").getAsString(), "Active");
        if (maCustomer == null) {
            commonResponseService.setResponse(response, 0, "Please provide flag", null);
            return response.toString();
        }
        
        commonResponseService.setResponse(response, 1, "Account has been logged in successfully", responseData);
        return response.toString();
    }
}
