/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
public class OTPVarifyDto {

    private String authorization;
    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
}
