/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.dtos;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
public class InvoiceResponsedto {

    @NotNull(message = "Please provide job id")
    private Long job_id;

 //   @DecimalMin(value = "0.0", inclusive = false)
  // @Digits(integer = 3, fraction = 2)
    @NotNull(message = "Please provide amount")
   // @NotBlank(message = "Please provide valid amount")
    private BigDecimal amount;

   
    private String comments;

}
