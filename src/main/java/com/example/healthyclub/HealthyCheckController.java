package com.example.healthyclub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthyCheckController {

    //브랜치 테스트 입니다
    @GetMapping("/hello")
    public String hello(){
        log.info("server is running!!");
        return "server is running";
    }

    @GetMapping("/")
    public String homepage(){




        return "";
    }

}
