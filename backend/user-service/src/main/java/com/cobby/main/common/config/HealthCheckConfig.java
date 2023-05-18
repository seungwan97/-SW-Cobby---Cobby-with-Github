package com.cobby.main.common.config;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Hidden
@RestController
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/config/HealthCheckConfig.java
@RequestMapping("/api/main/health")
=======
@RequestMapping("/api/user/health")
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/config/HealthCheckConfig.java
public class HealthCheckConfig {

    @GetMapping
    public void getHealth(){
        log.info("Health Check");
    }
}
