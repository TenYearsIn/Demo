package com.example.lophie.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class DiscoveryApplication extends SpringBootServletInitializer {

    public DiscoveryApplication(){

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DiscoveryApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DiscoveryApplication.class, args);
    }



}

