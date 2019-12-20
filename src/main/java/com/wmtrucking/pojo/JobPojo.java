/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wmtrucking.utils.DateUtils;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
public class JobPojo {

    String customername, jobname, jobdate,jobnumber, lodingaddress, dumpingaddress, drivername;
    Long id;
    

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getLodingaddress() {
        return lodingaddress;
    }

    public void setLodingaddress(String lodingaddress) {
        this.lodingaddress = lodingaddress;
    }

    public String getDumpingaddress() {
        return dumpingaddress;
    }

    public void setDumpingaddress(String dumpingaddress) {
        this.dumpingaddress = dumpingaddress;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Date getJobdate() {
//        return jobdate;
//    }
//    public String getJobdate() {
//        return new DateUtils().dateWithFormat(jobdate, "yyyy-MM-dd");
//    }

    public String getJobdate() {
        return new DateUtils().dateWithFormat(jobdate, "yyyy-MM-dd");
    }
    public void setJobdate(String jobdate) {
        this.jobdate = jobdate;
    }

}
