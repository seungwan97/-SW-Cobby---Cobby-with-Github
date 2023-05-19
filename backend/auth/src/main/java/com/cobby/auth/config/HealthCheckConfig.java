package com.cobby.auth.config;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Hidden
@RestController
@RequestMapping("/api/auth/health")
public class HealthCheckConfig {

    @GetMapping
    public void getHealth(){
        log.info("Health Check");
    }
}
