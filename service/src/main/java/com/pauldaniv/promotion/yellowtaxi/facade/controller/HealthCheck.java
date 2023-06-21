package com.pauldaniv.promotion.yellowtaxi.facade.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HealthCheck {

    @GetMapping("/health_check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("ok\n");
    }
}
