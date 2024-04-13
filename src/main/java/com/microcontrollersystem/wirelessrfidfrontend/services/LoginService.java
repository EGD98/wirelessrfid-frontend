package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@AllArgsConstructor
public class LoginService {

    private final SystemProperties properties;

    public String getLoginResponse(LoginRequest loginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/loginRequest", HttpMethod.POST, entity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (Objects.isNull(responseEntity.getBody()))
                return "bad credentials";
            return responseEntity.getBody().toString();
        } else {
            return "error sometime fail to get session";
        }
    }
}
