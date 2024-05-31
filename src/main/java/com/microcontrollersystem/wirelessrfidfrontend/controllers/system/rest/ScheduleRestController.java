package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ScheduleData;
import com.microcontrollersystem.wirelessrfidfrontend.services.ScheduleService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@AllArgsConstructor
public class ScheduleRestController {
    private final ScheduleService scheduleService;

    @PostMapping(value = "/schedule")
    public ResponseEntity<Object> addSchedule(HttpSession session, @RequestBody ScheduleData scheduleData) {
        Map<String, Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = scheduleService.addSchedule(tokenRetrieveString, scheduleData);
            map.put("status", 200);
            map.put("message", response);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar crear el horario", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al intentar crear el horario");
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PostMapping(value = "/deleteSchedule")
    public ResponseEntity<Object> deleteSpace(HttpSession session, @RequestBody Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = scheduleService.deleteSchedule(tokenRetrieveString, id);
            map.put("status", 200);
            map.put("message", response);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al eliminar el horario", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al eliminar el horario");
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PostMapping(value = "/getScheduleList")
    public ResponseEntity<Object> getSpaceList(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            List<ScheduleData> spaceDataList = scheduleService.getScheduleList(tokenRetrieveString);
            map.put("status", 200);
            map.put("message", spaceDataList);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la información", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al actualizar la información");
            return ResponseEntity.internalServerError().body(map);
        }
    }
}
