/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaJobs;
import com.wmtrucking.pojo.JobPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.wmtrucking.repository.JobRepository;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(MaJobs maJobs) {
        jobRepository.save(maJobs);
    }

    public List<MaJobs> findListOfJob(String status, Long driverId) {
        return jobRepository.findListOfJob(status, driverId);
    }

    public List<JobPojo> getJob(String satus, Long jobid,Long driverid) {
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer"
                + " where job_id=j.id))as customername ,j.jobdate,"
                + "j.jobname,j.jobnumber, j.lodingaddress,j.dumpingaddress,"
                + "(select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "                            from ma_jobs j where j.status=? and j.id=? and j.id in(select job_id from ma_job_driver where driver_id=? )";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, jobid,driverid}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public MaJobs findPendingJob(Long jobId, String status, Long driverId) {
        return jobRepository.findPendingJob(jobId, status, driverId);
    }

    public MaJobs findTotalJob(Long jobId, String status, Long driverId) {
        return jobRepository.findTotalJob(jobId, status, driverId);
    }

}
