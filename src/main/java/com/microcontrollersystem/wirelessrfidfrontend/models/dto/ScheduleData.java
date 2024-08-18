package com.microcontrollersystem.wirelessrfidfrontend.models.dto;

import lombok.Data;

@Data
public class ScheduleData {

    private String id;
    private ClientData clientData;
    private String startDate;
    private String endDate;
    private String startHour;
    private String endHour;
    private SpaceData spaceData;
    private String rfidCode;
}
