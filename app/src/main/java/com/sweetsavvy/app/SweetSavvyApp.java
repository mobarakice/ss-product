package com.sweetsavvy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.sweetsavvy.app",
        "com.sweetsavvy.authentication",
        "com.sweetsavvy.core"
})
@EntityScan(basePackages = {
        "com.sweetsavvy.core",
        "com.sweetsavvy.authentication"
})
@EnableJpaRepositories(basePackages = {
        "com.sweetsavvy.core.repository",
        "com.sweetsavvy.authentication.repository"
})
public class SweetSavvyApp {
    public static void main(String[] args) {
        SpringApplication.run(SweetSavvyApp.class, args);
    }
}