/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repository;

import com.wmtrucking.entity.MaCustomer;
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
public interface CustomerRepository extends JpaRepository<MaCustomer, Long> {

    @Query(nativeQuery = true, value = "select * from  ma_customer where phone=?1 and status=?2 ")
    MaCustomer findByPhoneAndStatus(String phone, String status);

}
