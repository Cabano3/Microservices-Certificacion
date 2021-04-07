package com.service.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.service.commonEnrollment.models.entity",
			 "com.service.courses.models.entity"})
public class ServiceCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCoursesApplication.class, args);
	}

}
