package com.microcontrollersystem.wirelessrfidfrontend.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system-properties")
@Data
public class SystemProperties {
    private String url;
    private String iconUrl;
}
