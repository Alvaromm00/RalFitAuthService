package com.prueba.personaltraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.prueba.personaltraining.*")
@EnableJpaRepositories("com.prueba.personaltraining.repository")
@EntityScan(basePackages = "com.prueba.personaltraining")
public class PersonalTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalTrainingApplication.class, args);
	}

}
