package com.example.springboot;

import com.example.springboot.repository.ChequeEntity;
import com.example.springboot.repository.ChequeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initializeDatabase(ChequeRepository repository) {

		return args -> {
			repository.save(new ChequeEntity());
			repository.save(new ChequeEntity());
			LOGGER.info("Initialized Database");
		};
	}
}
