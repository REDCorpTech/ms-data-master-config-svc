package com.ms.data.master.config.controller;

import com.ms.data.master.config.model.dto.config.OTPSendDTO;
import com.ms.data.master.config.service.OTPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${endpoint.otp-config.base}")
@RequiredArgsConstructor
@Slf4j
public class OTPController {
    private final OTPService otpService;

    @PostMapping(value = "${endpoint.otp-config.send}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendOtp() {
        otpService.generateAndSendOtp("christiantolie99@gmail.com");
        return ResponseEntity.ok("OTP sent to email");
    }

    @PostMapping("${endpoint.otp-config.validate}")
    public ResponseEntity<String> validateOtp(@RequestBody OTPSendDTO otpSendDTO) {
        return otpService.validateOtp("christiantolie99@gmail.com", otpSendDTO)
                ? ResponseEntity.ok("OTP is valid!")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
    }

}
