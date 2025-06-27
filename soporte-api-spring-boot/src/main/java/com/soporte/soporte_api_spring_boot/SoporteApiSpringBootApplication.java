package com.soporte.soporte_api_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.soporte"})
public class SoporteApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoporteApiSpringBootApplication.class, args);
	}

}
