package com.ezen.mood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodApplication.class, args);
    }

}
