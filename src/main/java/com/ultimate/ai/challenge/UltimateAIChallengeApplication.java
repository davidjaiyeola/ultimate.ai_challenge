package com.ultimate.ai.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.ultimate"})
@SpringBootApplication
public class UltimateAIChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(UltimateAIChallengeApplication.class, args);
    }
}
