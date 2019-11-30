/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaCustomer;
import com.wmtrucking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public MaCustomer findByPhoneAndStatus(String phone, String status) {
        return findByPhoneAndStatus(phone, status);
    }
}
