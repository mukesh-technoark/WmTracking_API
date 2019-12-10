/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.dtos;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
public class JobTruckingDto {

    public JobTruckingDto() {
    }
    @NotNull(message = "Please provide valid longitude")
    private BigDecimal longitude;

    @NotNull(message = "Please provide valid latitude")
    private BigDecimal latitude;
    
//    @NotNull(message = "Please provide valid driver id")
//    private Long driver_id;
    
    @NotNull(message = "Please provide valid job id")
    private Long job_id;

}
