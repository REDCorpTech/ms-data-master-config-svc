package com.ms.data.master.config.model.dto.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPConfigDTO {
    private UUID id;
    private String mailHost;
    private Integer mailPort;
    private String mailUsername;
    private String mailPassword;
    private String mailFromAddress;
    private String mailFromName;
    private String mailEncryption;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
