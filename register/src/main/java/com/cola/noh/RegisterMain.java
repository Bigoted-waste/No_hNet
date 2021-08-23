package com.cola.noh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RegisterMain {
    public static void main(String[] args) {
        SpringApplication.run(RegisterMain.class,args);
    }
}
