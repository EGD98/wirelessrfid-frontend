package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class LoginService {

    private final SystemProperties properties;

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginResponse> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/loginRequest", HttpMethod.POST, entity, LoginResponse.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
