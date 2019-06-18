package com.scalefocus.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@ManagementContextConfiguration
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}