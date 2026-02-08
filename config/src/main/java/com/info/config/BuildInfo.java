package com.info.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "build")
@Data
public class BuildInfo {
    private String id;
    private String version;
    private String name;
}
