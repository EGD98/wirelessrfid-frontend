package com.microcontrollersystem.wirelessrfidfrontend.services;

import com.microcontrollersystem.wirelessrfidfrontend.configuration.SystemProperties;
import com.microcontrollersystem.wirelessrfidfrontend.models.dto.RegistryData;
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
public class RegistryService {
    private final SystemProperties properties;

    public List<RegistryData> getRegistryList(String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<RegistryData>> responseEntity = restTemplate.exchange(properties.getUrlBackend() +
                "/api/registryList", HttpMethod.POST, entity, new ParameterizedTypeReference<List<RegistryData>>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (Objects.isNull(responseEntity.getBody()))
                return null;
            return responseEntity.getBody();
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new Exception("UNAUTHORIZED");
        }
        return null;
    }
}
