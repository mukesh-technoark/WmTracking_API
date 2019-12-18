/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaJobTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.wmtrucking.repository.JobTransactionRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class JobTransactionService {

    @Autowired
    JobTransactionRepository jobTransactionRepository;

    public void save(MaJobTransaction maJobTransaction) {
        jobTransactionRepository.save(maJobTransaction);
    }

    public MaJobTransaction findTransaction(Long driverId, Long jobid, String TransactionStatus) {
        return jobTransactionRepository.findTransaction(driverId, jobid, TransactionStatus);
    }

    public List<MaJobTransaction> totalAvailableJob(Long jobid, Long totaljob) {
        return jobTransactionRepository.totalAvailableJob(jobid, totaljob);
    }

    public Long totalJobTransactionCount(Long jobid) {
        return jobTransactionRepository.totalJobTransactionCount(jobid);
    }
}
