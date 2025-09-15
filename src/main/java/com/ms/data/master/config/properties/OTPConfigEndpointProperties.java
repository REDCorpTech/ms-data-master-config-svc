package com.ms.data.master.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoint.otp-config")
@Getter
@Setter
public class OTPConfigEndpointProperties {
    private String base;
    private String getAll;
    private String getById;
    private String update;
    private String delete;
    private String send;
    private String validate;
}
