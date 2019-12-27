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
public class DriverPojo {

//    int pickupcount, dumpcount, jobcount;
    int todaypickup, todaydump, alldump;

//    public DriverPojo(int pickupcount, int dumpcount, int jobcount) {
//        this.pickupcount = pickupcount;
//        this.dumpcount = dumpcount;
//        this.jobcount = jobcount;
//    } 
    public DriverPojo(int todaypickup, int todaydump, int alldump) {
        this.todaypickup = todaypickup;
        this.todaydump = todaydump;
        this.alldump = alldump;
    }

    public int getTodaypickup() {
        return todaypickup;
    }

    public void setTodaypickup(int todaypickup) {
        this.todaypickup = todaypickup;
    }

    public int getTodaydump() {
        return todaydump;
    }

    public void setTodaydump(int todaydump) {
        this.todaydump = todaydump;
    }

    public int getAlldump() {
        return alldump;
    }

    public void setAlldump(int alldump) {
        this.alldump = alldump;
    }

}
