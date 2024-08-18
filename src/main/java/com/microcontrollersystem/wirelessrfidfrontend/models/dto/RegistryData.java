package com.microcontrollersystem.wirelessrfidfrontend.models.dto;

import lombok.Data;

@Data
public class RegistryData {
    private String id;
    private String codeRfid;
    private String date;
    private String hour;
    private String statusRfid;
}
