/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.wmtrucking.repository.DriverRepository;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public void save(MaDriver maDriver) {
        driverRepository.save(maDriver);
    }

    public MaDriver findByPhoneAndStatus(String phone, String status) {
        return driverRepository.findByPhoneAndStatus(phone, status);
    }

    public MaDriver findByUserForOTPvarify(Long otp, String status,Long id) {
        return driverRepository.findByUserForOTPvarify(otp, status, id );

    }
}
