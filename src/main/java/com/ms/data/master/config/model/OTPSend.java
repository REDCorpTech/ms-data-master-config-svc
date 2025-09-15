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
        name = "t_otp_send",
        schema = "ms-data-master-config-svc" // must match your DB schema
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPSend {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "email_send")
    private String email;

    @Column(name = "otp_number")
    private String otp;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
