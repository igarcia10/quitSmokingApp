package com.everis.becaeoi.quitSmoking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.everis.becaeoi.quitSmoking"})
@EntityScan("com.everis.becaeoi.quitSmoking.persistence.entity")
public class ITApplication {

	public static void main(String[] args) {
		SpringApplication.run(ITApplication.class, args);
	}
}
