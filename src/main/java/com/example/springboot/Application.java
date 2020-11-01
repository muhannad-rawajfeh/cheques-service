package com.example.springboot;

import controller.AccountEntity;
import controller.ChequeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ChequesRepository repository) {
		return args -> {

			repository.save(new ChequeEntity(new BigDecimal(1000), "0001", "02",
					new AccountEntity("11", "22", "33"),
					new AccountEntity("44", "55", "66")));

			for (ChequeEntity chequeEntity : repository.findAll()) {
				LOGGER.info(chequeEntity.toString());
			}

		};
	}

}
