package com.promocion.promocion_api_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.promocion"})
public class PromocionApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromocionApiSpringBootApplication.class, args);
	}

}
