package io.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@SpringBootApplication(scanBasePackages = {"io.security.corespringsecurity"})
public class CorespringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorespringsecurityApplication.class, args);
    }

}
