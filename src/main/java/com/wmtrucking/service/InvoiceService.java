/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.service;

import com.wmtrucking.entity.MaInvoice;
import com.wmtrucking.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public void save(MaInvoice maInvoice) {
        invoiceRepository.save(maInvoice);
    }
}
