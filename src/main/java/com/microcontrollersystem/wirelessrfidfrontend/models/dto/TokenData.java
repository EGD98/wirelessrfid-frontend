package com.microcontrollersystem.wirelessrfidfrontend.models.dto;

import lombok.Data;

@Data
public class TokenData {
    private Integer jti;
    private Long iat;
    private String sub;
    private String iss;
    private Long exp;
}
