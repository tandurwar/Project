package com.real.estate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.real.estate")
public class EstateApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateApplication.class, args);
	}

}
