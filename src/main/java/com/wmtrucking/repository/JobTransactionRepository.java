/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repository;

import com.wmtrucking.entity.MaJobTransaction;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Scope(value = "request")
public interface JobTransactionRepository extends JpaRepository<MaJobTransaction, Long> {

    @Query(nativeQuery = true, value = "select * from ma_job_transaction where driverid=?1 and job_id=?2 and status=?3")
    MaJobTransaction findTransaction(Long driverId, Long jobid, String TransactionStatus);

    @Query(nativeQuery = true, value = "select * from ma_job_transaction where (select count(*) from ma_job_transaction where "
            + "job_id=?1) = ?2 ")
    List<MaJobTransaction> totalAvailableJob(Long jobid, Long totaljob);

    @Query(nativeQuery = true, value = "select count(*) from ma_job_transaction where job_id=?1")
     Long  totalJobTransactionCount(Long jobid);

}
