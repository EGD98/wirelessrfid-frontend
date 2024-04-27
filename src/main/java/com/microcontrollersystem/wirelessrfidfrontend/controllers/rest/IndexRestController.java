package com.microcontrollersystem.wirelessrfidfrontend.controllers.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.UserData;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import com.microcontrollersystem.wirelessrfidfrontend.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@Slf4j
public class IndexRestController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping(value = "/login")
    public Map<String,Object> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String token = loginService.getLoginResponse(loginRequest);
        Map <String,Object> map = new HashMap<>();
        if (Objects.nonNull(token)) {
            session.setAttribute("token", token);
            map.put("status",200);
            map.put("message","user authorized");
            return map;
        }
        map.put("status",401);
        map.put("message","user not authorized");
        return map;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<Object> addUser(HttpSession session, @RequestBody UserData userData) {
        Map <String,Object> map = new HashMap<>();
        try {
            Object tokenRetrieve = session.getAttribute("token");
            String tokenRetrieveString = tokenRetrieve.toString();
            String response = userService.addUser(tokenRetrieveString, userData);
            map.put("status", 200);
            map.put("message", response);
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar crear el usuario", e);
            map.put("status", 200);
            map.put("message", "Ha ocurrido un error al intentar crear el usuario");
            return ResponseEntity.internalServerError().body(map);
        }
    }
}
