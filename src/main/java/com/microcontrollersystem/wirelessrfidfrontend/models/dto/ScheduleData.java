package com.microcontrollersystem.wirelessrfidfrontend.models.dto;

import lombok.Data;

@Data
public class ScheduleData {

    private String id;
    private String idClient;
    private String startDate;
    private String endDate;
    private String startHour;
    private String endHour;
    private String idSpace;
}
