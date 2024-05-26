package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.SpaceData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class SpaceService {
    private final SystemProperties properties;

    public List<SpaceData> getSpaceList(String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<SpaceData>> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/spaceList", HttpMethod.POST, entity, new ParameterizedTypeReference<List<SpaceData>>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (Objects.isNull(responseEntity.getBody()))
                return null;
            return responseEntity.getBody();
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new Exception("UNAUTHORIZED");
        }
        return null;
    }
    public String addSpace(String token, SpaceData spaceData){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<SpaceData> entity = new HttpEntity<>(spaceData, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/space", HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    public String deleteSpace(String token,Integer id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<Integer> entity = new HttpEntity<>(id, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/deleteSpace", HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }
}
