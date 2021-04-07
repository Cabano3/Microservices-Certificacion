package com.service.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCoursesApplication.class, args);
	}

}
