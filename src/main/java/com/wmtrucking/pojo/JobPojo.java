/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wmtrucking.utils.DateUtils;

/**
 *
 * @author Admin
 */
public class JobPojo {

    String customername, jobname, jobdate, jobnumber, lodingaddress, dumpingaddress, drivername, jobstatus, startstatus;
    Long id;
    @JsonIgnore
    int totaljobcount;
    @JsonIgnore
    int donejobcount;
    @JsonIgnore
    int pickupcount;
    @JsonIgnore
    int dumpcount;

    @JsonIgnore
    int todaytotaldriverpickupcount;
    @JsonIgnore
    int todaytotaldriverdumpcount;
    @JsonIgnore
    int driverdonejobcount;

    private DriverPojo driver;
    private JobCountPojo job;

    public JobCountPojo getJob() {
        job = new JobCountPojo(totaljobcount, donejobcount, pickupcount, dumpcount);
        return job;
    }

    public DriverPojo getDriver() {
        driver = new DriverPojo(todaytotaldriverpickupcount, todaytotaldriverdumpcount, driverdonejobcount);
        return driver;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getJobname() {
        return jobname;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getStartstatus() {
        return startstatus;
    }

    public void setStartstatus(String startstatus) {
        this.startstatus = startstatus;
    }

    public int getTotaljobcount() {
        return totaljobcount;
    }

    public void setTotaljobcount(int totaljobcount) {
        this.totaljobcount = totaljobcount;
    }

    public int getDonejobcount() {
        return donejobcount;
    }

    public void setDonejobcount(int donejobcount) {
        this.donejobcount = donejobcount;
    }

    public int getPickupcount() {
        return pickupcount;
    }

    public void setPickupcount(int pickupcount) {
        this.pickupcount = pickupcount;
    }

    public int getDumpcount() {
        return dumpcount;
    }

    public void setDumpcount(int dumpcount) {
        this.dumpcount = dumpcount;
    }

    public int getTodaytotaldriverpickupcount() {
        return todaytotaldriverpickupcount;
    }

    public void setTodaytotaldriverpickupcount(int todaytotaldriverpickupcount) {
        this.todaytotaldriverpickupcount = todaytotaldriverpickupcount;
    }

    public int getTodaytotaldriverdumpcount() {
        return todaytotaldriverdumpcount;
    }

    public void setTodaytotaldriverdumpcount(int todaytotaldriverdumpcount) {
        this.todaytotaldriverdumpcount = todaytotaldriverdumpcount;
    }

    public int getDriverdonejobcount() {
        return driverdonejobcount;
    }

    public void setDriverdonejobcount(int driverdonejobcount) {
        this.driverdonejobcount = driverdonejobcount;
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
