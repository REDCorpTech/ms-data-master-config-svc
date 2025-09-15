package com.ms.data.master.config.respository;

import com.ms.data.master.config.model.OTPSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OTPSendRepository extends JpaRepository<OTPSend, UUID> {
    Optional<OTPSend> findTopByEmailOrderByCreatedAtDesc(String email);
}
