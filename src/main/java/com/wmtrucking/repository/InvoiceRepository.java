/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repository;

import com.wmtrucking.entity.MaInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface InvoiceRepository extends JpaRepository<MaInvoice , Long>{
    
}
