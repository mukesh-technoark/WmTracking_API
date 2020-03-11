/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repository;

import com.wmtrucking.entity.MaPushNotification;
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
public interface PushNotifcationRepository extends JpaRepository<MaPushNotification, Long>{
   
     @Query(nativeQuery = true, value = "select p.* from ma_push_notification p where p.driverid=?1 and p.status=?2 and p.devicetoken=?3 limit 1")
    MaPushNotification findAlreadyExist(Long driverid, String status, String devicetoken);
}
