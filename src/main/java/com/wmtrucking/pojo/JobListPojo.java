/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

import com.wmtrucking.utils.DateUtils;

/**
 *
 * @author Admin
 */
public class JobListPojo {

    Long id;
    int totaljobcount, driverjobcount, totalDoneJobCount, invoice;
    String jobname, jobnumber, lodingaddress, dumpingaddress, jobStatus, jobdate, invoiceurl, startstatus,drivername;
    String lodingLatitude, lodingLongitude, dumpingLatitude, dumpingLongitude;
   

//    public String getInvoiceurl() {
//        return Constant.INVOICEURL+String.valueOf(id);
//    }
    public void setInvoiceurl(String invoiceurl) {
        this.invoiceurl = invoiceurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getStartstatus() {
        return startstatus == null ? "" : startstatus;
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

    public int getDriverjobcount() {
        return driverjobcount;
    }

    public void setDriverjobcount(int driverjobcount) {
        this.driverjobcount = driverjobcount;
    }

    public int getTotalDoneJobCount() {
        return totalDoneJobCount;
    }

    public void setTotalDoneJobCount(int totalDoneJobCount) {
        this.totalDoneJobCount = totalDoneJobCount;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
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

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobdate() {
        return new DateUtils().dateWithFormat(jobdate, "yyyy-MM-dd");
    }

    public void setJobdate(String jobdate) {
        this.jobdate = jobdate;
    }

    public String getLodingLatitude() {
        return lodingLatitude;
    }

    public void setLodingLatitude(String lodingLatitude) {
        this.lodingLatitude = lodingLatitude;
    }

    public String getLodingLongitude() {
        return lodingLongitude;
    }

    public void setLodingLongitude(String lodingLongitude) {
        this.lodingLongitude = lodingLongitude;
    }

    public String getDumpingLatitude() {
        return dumpingLatitude;
    }

    public void setDumpingLatitude(String dumpingLatitude) {
        this.dumpingLatitude = dumpingLatitude;
    }

    public String getDumpingLongitude() {
        return dumpingLongitude;
    }

    public void setDumpingLongitude(String dumpingLongitude) {
        this.dumpingLongitude = dumpingLongitude;
    }



}
