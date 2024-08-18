package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.RegistryData;
import com.microcontrollersystem.wirelessrfidfrontend.services.RegistryService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class RegistryRestController {
    private RegistryService registryService;

    @PostMapping(value ="/getRegistryList")
    public ResponseEntity<Object> getResgitryList(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            List<RegistryData> registryDataList = registryService.getRegistryList(tokenRetrieveString);
            map.put("status", 200);
            map.put("message", registryDataList);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la información", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al actualizar la información");
            return ResponseEntity.internalServerError().body(map);
        }
    }
}
