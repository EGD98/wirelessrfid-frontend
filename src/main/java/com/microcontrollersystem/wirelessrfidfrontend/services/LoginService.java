package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.google.gson.Gson;
import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.LoginResponse;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.TokenData;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
@Slf4j
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

    public TokenData validateToken (HttpSession session) throws Exception {
        Object tokenRetrieve = session.getAttribute("token");
        String token = tokenRetrieve.toString();
        String[] tokenArray = token.split("\\.");
        String payload = new String(Base64.getDecoder().decode(tokenArray[1] ), StandardCharsets.UTF_8);
        TokenData tokenData = new Gson().fromJson(payload, TokenData.class);
        Instant tokenExpiration = Instant.ofEpochSecond(tokenData.getExp());
        if (tokenExpiration.isBefore(Instant.now())){
            session.removeAttribute("token");
            throw new Exception("TOKEN EXPIRADO!");
        }
        return tokenData;
    }
}
