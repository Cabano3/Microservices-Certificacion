package com.service.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.service.commonEnrollment.models.entity",
			 "com.service.students.models.entity"})
public class ServiceStudentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceStudentsApplication.class, args);
	}

}
