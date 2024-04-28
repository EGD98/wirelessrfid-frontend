package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.UserData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class CatalogService {
    private final SystemProperties properties;

    public List<Map<String, Object>> getCatalog(String token, String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<Object> entity = new HttpEntity<>(name, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/getCatalog", HttpMethod.POST, entity, Object.class);

        return (List<Map<String, Object>>) responseEntity.getBody();
    }
}
