package com.emp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeePayManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePayManagementApplication.class, args);
	}

	@Bean
	public void test() {
		System.out.println("Hello ... Java");
	}
}
