package com.deepblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JavaSpringbatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringbatchApplication.class, args);
    }

}
