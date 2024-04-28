package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.UserData;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final SystemProperties properties;

    public List<UserData> getUserList(String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/userList", HttpMethod.POST, entity, Object.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (Objects.isNull(responseEntity.getBody()))
                return null;
            return (List<UserData>) responseEntity.getBody();
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new Exception("UNAUTHORIZED");
        }
        return null;
    }

    public String addUser(String token, UserData userData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<UserData> entity = new HttpEntity<>(userData, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/user", HttpMethod.POST, entity, String.class);

        return responseEntity.getBody();
    }

    public String deleteUser(String token,Integer id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<Integer> entity = new HttpEntity<>(id, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/deleteUser", HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }
}
