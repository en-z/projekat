package com.example.service.osoblje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.service.osoblje.client")
public class OsobljeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsobljeApplication.class, args);
	}

}
