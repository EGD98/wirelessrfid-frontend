package com.microcontrollersystem.wirelessrfidfrontend.controllers.rest;

import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginResponse;
import com.microcontrollersystem.wirelessrfidfrontend.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IndexRestController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.getLoginResponse(loginRequest);
    }
}
