package com.entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EntregaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EntregaApplication.class, args);
	}

}