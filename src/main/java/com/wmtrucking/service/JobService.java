/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.wmtrucking.repository.JobRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public void save(MaJobs maJobs) {
        jobRepository.save(maJobs);
    }

    public List<MaJobs> findListOfJob(String status, String jobStatus, Long driverId) {
        return jobRepository.findListOfJob(status, jobStatus, driverId);
    }

    public MaJobs findPendingJob(Long jobId, String status,   Long driverId) {
        return jobRepository.findPendingJob(jobId, status, driverId);
    }

}
