package com.ms.data.master.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoint.config-enum")
@Getter
@Setter
public class ConfigEnumEndpointProperties {
    private String base;
    private String getAll;
    private String getById;
    private String getByType;
    private String type;
    private String update;
    private String delete;
}
