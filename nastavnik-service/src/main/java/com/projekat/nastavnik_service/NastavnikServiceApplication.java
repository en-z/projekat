package com.projekat.nastavnik_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.projekat.nastavnik_service.client")
public class NastavnikServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NastavnikServiceApplication.class, args);
	}

}
