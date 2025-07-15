package com.pm.EmployeeManagementPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class EmployeeManagementPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementPortalApplication.class, args);
		System.out.println("Application Started...");
	}

}
