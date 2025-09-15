package com.ms.data.master.config.respository;

import com.ms.data.master.config.model.OTPConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OTPConfigRepository extends JpaRepository<OTPConfig, UUID> {
    Optional<OTPConfig> findTopByOrderByCreatedAtDesc();
}
