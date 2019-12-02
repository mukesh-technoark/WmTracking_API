/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.utils;

import com.wmtrucking.exceptions.InvalidHeaderException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;


@Component
public class AppUtil {

    public void checkHeaders(HttpServletRequest request) throws InvalidHeaderException {
        if (!isValidHeader(request.getHeader(Constant.TOKEN.toString()))) {
            throw new InvalidHeaderException(Constant.INVALIDAPPTOKEN.toString());
        }
        if (request.getHeader(Constant.DEVICETOKEN.toString()) == null || request.getHeader(Constant.DEVICETOKEN.toString()).equals("")) {
            throw new InvalidHeaderException("Device token not found.");
        }
    }

    public void checkHeadersWithAuth(HttpServletRequest request) throws InvalidHeaderException {
        if (!isValidHeader(request.getHeader(Constant.TOKEN.toString()))) {
            throw new InvalidHeaderException(Constant.INVALIDAPPTOKEN.toString());
        }
        if (request.getHeader(Constant.DEVICETOKEN.toString()) == null || request.getHeader(Constant.DEVICETOKEN.toString()).equals("")) {
            throw new InvalidHeaderException("Device token not found.");
        }
        if (request.getHeader(Constant.AUTHORIZATION.toString()) == null || request.getHeader(Constant.AUTHORIZATION.toString()).equals("")) {
            throw new InvalidHeaderException("Authorization token not found.");
        }
    }

    public String getJwt(HttpServletRequest request) {
        try {
            return request.getHeader(Constant.AUTHORIZATION.toString());
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isValidHeader(String value) {
        if (value == null || value.equals("")) {
            return false;
        } else {
            return value.equals(Constant.APPTOKEN.toString());
        }
    }

}
