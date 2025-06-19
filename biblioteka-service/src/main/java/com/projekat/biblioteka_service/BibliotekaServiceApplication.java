package com.projekat.biblioteka_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.projekat.biblioteka_service.client")
public class BibliotekaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotekaServiceApplication.class, args);
	}

}
