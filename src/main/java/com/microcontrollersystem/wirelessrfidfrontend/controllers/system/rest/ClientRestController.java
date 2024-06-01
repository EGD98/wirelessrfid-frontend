package com.microcontrollersystem.wirelessrfidfrontend.controllers.system.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidfrontend.services.ClientService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

    @PostMapping(value = "/client")
    public ResponseEntity<Object> addClient(HttpSession session, @RequestBody ClientData clientData) {
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = clientService.addClient(tokenRetrieveString, clientData);
            map.put("status", 200);
            map.put("message", response);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar crear el cliente", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al intentar crear el cliente");
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PostMapping(value = "/deleteClient")
    public ResponseEntity<Object> deleteClient(HttpSession session, @RequestBody Integer id) {
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = clientService.deleteClient(tokenRetrieveString, id);
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

    @PostMapping(value = "/getClientList")
    public ResponseEntity<Object> getClientrList(HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            List<ClientData> clientDataList = clientService.getClientList(tokenRetrieveString);
            map.put("status", 200);
            map.put("message", clientDataList);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la información", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al actualizar la información");
            return ResponseEntity.internalServerError().body(map);
        }
    }
    @GetMapping(value = "/completeClientList/{query}")
    public ResponseEntity<Object> getCompleteClientList(HttpSession session,@PathVariable String query) {
        Map<String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            List<ClientData> clientDataList = clientService.getClientList(tokenRetrieveString);
            map.put("status", 200);
            map.put("message", clientDataList);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la información", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al actualizar la información");
            return ResponseEntity.internalServerError().body(map);
        }
    }
}
