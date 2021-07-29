package com.ibs.litmus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages ="com.ibs.litmus.controllerk")
@EnableJpaRepositories(basePackages = "com.ibs.litmus.repositoryk")
@EntityScan(basePackages = "com.ibs.litmus.modelk")
public class TaskOneApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TaskOneApplication.class, args);
	}

}
