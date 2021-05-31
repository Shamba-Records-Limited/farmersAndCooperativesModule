package com.shabarecords.farmersmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient        // Enable eureka client.
public class FarmersModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmersModuleApplication.class, args);
    }

}
