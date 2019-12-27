/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

public class JobCountPojo {

//    int count, donecount, pickupcount, dumpcount;
    int totaljob, alldump, todaypickup, todaydump;

    public JobCountPojo(int totaljob, int alldump, int todaypickup, int todaydump) {
        this.totaljob = totaljob;
        this.alldump = alldump;
        this.todaypickup = todaypickup;
        this.todaydump = todaydump;
    }

    public int getTotaljob() {
        return totaljob;
    }

    public void setTotaljob(int totaljob) {
        this.totaljob = totaljob;
    }

    public int getAlldump() {
        return alldump;
    }

    public void setAlldump(int alldump) {
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

}
