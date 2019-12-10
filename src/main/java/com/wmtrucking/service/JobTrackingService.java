/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaJobTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.wmtrucking.repository.JobTrackingRepository;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class JobTrackingService {

    @Autowired
    JobTrackingRepository jobTrackingRepository;

    public void save(MaJobTracking maJobTracking) {
        jobTrackingRepository.save(maJobTracking);
    }

}
