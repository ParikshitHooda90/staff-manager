package com.vaahano.staffmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StaffManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffManagerApplication.class, args);
	}

}
