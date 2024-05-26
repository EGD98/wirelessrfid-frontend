package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.SpaceData;
import com.microcontrollersystem.wirelessrfidfrontend.services.SpaceService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class SpaceRestController {
    private final SpaceService spaceService;

    @PostMapping(value = "/space")
    public ResponseEntity<Object> addSpace(HttpSession session, @RequestBody SpaceData spaceData) {
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = spaceService.addSpace(tokenRetrieveString, spaceData);
            map.put("status", 200);
            map.put("message", response);
            return ResponseEntity.ok().body(map);
        }catch (Exception e){
            log.error("Ha ocurrido un error al intentar crear el espacio", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al intentar crear el espacio");
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PostMapping(value = "/deleteSpace")
    public ResponseEntity<Object> deleteSpace(HttpSession session, @RequestBody Integer id) {
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = spaceService.deleteSpace(tokenRetrieveString, id);
            map.put("status", 200);
            map.put("message", response);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al eliminar cliente", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al eliminar cliente");
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PostMapping(value = "/getSpaceList")
    public ResponseEntity<Object> getSpaceList(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            List<SpaceData> spaceDataList = spaceService.getSpaceList(tokenRetrieveString);
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
