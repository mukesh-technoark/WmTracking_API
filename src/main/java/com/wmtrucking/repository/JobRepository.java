/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repository;

import com.wmtrucking.entity.MaJobs;
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
public interface JobRepository extends JpaRepository<MaJobs, Long> {

    
//    @Query(nativeQuery = true, value = "select u.* from  ma_jobs u where u.status=?1 and u.job_status=?2 and u.id in(select job_id from ma_job_driver where "
//            + " driver_id=?3 ) ")
//    List<MaJobs> findListOfJob(String status, String jobStatus, Long driverId);
    @Query(nativeQuery = true, value = "select u.* from  ma_jobs u where u.status=?1 and  u.id in(select job_id from ma_job_driver where "
            + " driver_id=?2 ) ")
    List<MaJobs> findListOfJob(String status, Long driverId);

//    @Query(nativeQuery = true, value = "select * from  ma_jobs where id=?1 and status=?2 and job_status=?3 and id in(select job_id from ma_job_driver "
//            + "where  driver_id=?4 ) ")
//    MaJobs findPendingJob(Long jobId, String status, String jobStatus,Long driverId );
    @Query(nativeQuery = true, value = "select * from  ma_jobs where id=?1 and status=?2  and id in(select job_id from ma_job_driver "
            + "where  driver_id=?3 ) ")
    MaJobs findPendingJob(Long jobId, String status, Long driverId);

    @Query(nativeQuery = true, value = "select * from  ma_jobs where id=?1 and status=?2 and "
            + "id in(select job_id from ma_job_driver where driver_id=?3)")
    MaJobs findTotalJob(Long jobId, String status, Long driverId);

    @Query(nativeQuery = true, value = "select * from  ma_jobs where id=?1 and status=?2 and job_status=?4 and "
            + "id in(select job_id from ma_job_driver where driver_id=?3 and driver_id not in(select driverid from ma_invoice where jobid=?1 ))")
    MaJobs findCompletJob(Long jobId, String status, Long driverId, String job_status);

}
