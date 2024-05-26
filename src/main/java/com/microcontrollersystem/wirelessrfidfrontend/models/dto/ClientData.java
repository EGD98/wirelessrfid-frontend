package com.microcontrollersystem.wirelessrfidfrontend.models.dto;

import lombok.Data;

@Data
public class ClientData {

    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String  phoneNumber;
    private String email;
    private String rfidCode;
    private String space;
    private String  admissionDate;
    private String  egressDate;
    private String  admissionTime;
    private String  egressTime;
}
