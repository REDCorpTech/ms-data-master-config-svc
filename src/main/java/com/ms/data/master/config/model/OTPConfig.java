package com.ms.data.master.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "t_otp_config",
        schema = "ms-data-master-config-svc" // must match your DB schema
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPConfig {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "mail_host")
    private String mailHost;

    @Column(name = "mail_port")
    private Integer mailPort;

    @Column(name = "mail_username")
    private String mailUsername;

    @Column(name = "mail_password")
    private String mailPassword;

    @Column(name = "mail_from_address")
    private String mailFromAddress;

    @Column(name = "mail_from_name")
    private String mailFromName;

    @Column(name = "mail_encryption")
    private String mailEncryption;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
