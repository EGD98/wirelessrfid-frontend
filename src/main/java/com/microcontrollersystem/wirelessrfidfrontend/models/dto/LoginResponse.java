package com.microcontrollersystem.wirelessrfidfrontend.models.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String name;
    private String email;
    private String userName;
    private String idUserType;
    private String idCorporation;
}
