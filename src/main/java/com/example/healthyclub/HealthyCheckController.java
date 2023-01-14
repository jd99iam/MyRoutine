package com.example.healthyclub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@SpringBootApplication
public class HealthyCheckController {

    @GetMapping("/hello")
    public String hello(){
        log.info("server is running");
        return "server is running";
    }
}
