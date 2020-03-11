/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.dtos;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
public class Authenticationdto {

    public Authenticationdto() {
    }

    @NotNull(message = "Please provide valid id")

//    @NotNull(message = "Please provide phone number")
//    private String phone;
//    @NotNull(message = "Please provide valid Country code")
//    private String countryCode;
    @NotNull(message = "Please provide Email")
    private String email;

//    @NotNull(message = "Please provide devicetocken")
//    private String devicetocken;

 

    @NotNull(message = "Please provide password")
    private String password;

}
