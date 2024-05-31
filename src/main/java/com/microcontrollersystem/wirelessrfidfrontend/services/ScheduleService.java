package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.ScheduleData;
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
public class ScheduleService {
    private final SystemProperties properties;

    public List<ScheduleData> getScheduleList(String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<ScheduleData>> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/scheduleList", HttpMethod.POST, entity, new ParameterizedTypeReference<List<ScheduleData>>() {
        });
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (Objects.isNull(responseEntity.getBody()))
                return null;
            return responseEntity.getBody();
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new Exception("UNAUTHORIZED");
        }
        return null;
    }

    public String addSchedule(String token, ScheduleData scheduleData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<ScheduleData> entity = new HttpEntity<>(scheduleData, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/schedule", HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    public String deleteSchedule(String token, Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<Integer> entity = new HttpEntity<>(id, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/deleteSchedule", HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }
}
