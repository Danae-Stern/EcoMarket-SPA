package com.metodo_pago.metodo_pago_api_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.metodo_pago"})
public class MetodoPagoApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetodoPagoApiSpringBootApplication.class, args);
	}

}
