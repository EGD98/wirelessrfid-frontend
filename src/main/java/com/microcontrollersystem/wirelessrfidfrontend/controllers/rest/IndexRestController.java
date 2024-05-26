package com.microcontrollersystem.wirelessrfidfrontend.controllers.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


}
