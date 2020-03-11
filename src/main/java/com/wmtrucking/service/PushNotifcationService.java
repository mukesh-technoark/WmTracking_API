/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaPushNotification;
import com.wmtrucking.repository.PushNotifcationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class PushNotifcationService {

    @Autowired
    PushNotifcationRepository pushNotifcationRepository;

    public void save(MaPushNotification maPushNotification) {
        pushNotifcationRepository.save(maPushNotification);
    }

    public MaPushNotification findAlreadyExist(Long driverid, String status, String devicetoken) {
        return pushNotifcationRepository.findAlreadyExist(driverid, status, devicetoken);
    }
}
