package com.practice.taveboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaveboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaveboardApplication.class, args);
    }

}
