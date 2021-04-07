package com.service.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.service.commonEnrollment.models.entity"})
public class ServiceEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEnrollmentApplication.class, args);
	}

}
