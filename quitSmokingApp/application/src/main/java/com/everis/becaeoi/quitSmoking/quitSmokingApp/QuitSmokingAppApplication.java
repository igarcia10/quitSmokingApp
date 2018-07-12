package com.everis.becaeoi.quitSmoking.quitSmokingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.everis.becaeoi.quitSmoking"})
@EntityScan("com.everis.becaeoi.quitSmoking.persistence.entity")
@EnableJpaRepositories("com.everis.becaeoi.quitSmoking.persistence.repository")
public class QuitSmokingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuitSmokingAppApplication.class, args);
	}
}
