package com.ms.data.master.config.service;

import com.ms.data.master.config.model.OTPConfig;
import com.ms.data.master.config.model.OTPSend;
import com.ms.data.master.config.model.dto.config.OTPSendDTO;
import com.ms.data.master.config.respository.OTPConfigRepository;
import com.ms.data.master.config.respository.OTPSendRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OTPService {
    private final OTPSendRepository otpSendRepository;
    private final OTPConfigRepository otpConfigRepository;

    public void generateAndSendOtp(String toEmail) {
        // 1. Get config from DB
        OTPConfig config = otpConfigRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new IllegalStateException("No OTP config found"));

        // 2. Generate 6 digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // 3. Save to DB
        OTPSend entity = OTPSend.builder()
                .email(toEmail)
                .otp(otp)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .createdAt(LocalDateTime.now())
                .build();
        otpSendRepository.save(entity);

        // 4. Build JavaMailSender dynamically
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(config.getMailHost());
        mailSender.setPort(config.getMailPort());
        mailSender.setUsername(config.getMailUsername());
        mailSender.setPassword(config.getMailPassword());

        Properties props = mailSender.getJavaMailProperties();
        if ("ssl".equalsIgnoreCase(config.getMailEncryption())) {
            props.put("mail.smtp.ssl.enable", "true");
        } else if ("tls".equalsIgnoreCase(config.getMailEncryption())) {
            props.put("mail.smtp.starttls.enable", "true");
        }
        props.put("mail.smtp.auth", "true");

        // 5. Send email
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(config.getMailFromAddress());
            message.setTo(toEmail);
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + otp + " (valid for 5 minutes)");
            mailSender.send(message);
            log.info("OTP sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send OTP email: {}", e.getMessage(), e);
            throw new RuntimeException("Could not send OTP email", e);
        }
    }

    public boolean validateOtp(String email, OTPSendDTO otpSendDTO) {
        return otpSendRepository.findTopByEmailOrderByCreatedAtDesc(email)
                .filter(savedOtp ->
                        savedOtp.getOtp().equals(otpSendDTO.getOtp()) &&
                                savedOtp.getExpiryTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }

}
