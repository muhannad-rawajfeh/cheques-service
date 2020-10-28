package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class ChequesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChequesController.class);

	private final ChequesRepository repository;

	public ChequesController(ChequesRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/cheques")
	public void createCheque() {
		LOGGER.info("Cheque Created");
		repository.save(new Cheque(new BigDecimal(5000), "0003", "02",
				new Account("11", "22", "33"),
				new Account("44", "55", "66")));
	}

//	@PutMapping("/cheques/{id}")
//	public void updateCheque(@PathVariable Long id) {
//		Optional<Cheque> cheque = repository.findById(id);
//		if (!cheque.isPresent()) {
//			LOGGER.info("Cheque does not exist");
//		}
//		else {
//
//		}
//	}

	@GetMapping("/cheques")
	public String listCheques() {
		LOGGER.info("Cheques List");
		return repository.findAll().toString();
	}

	@GetMapping("/cheques/{id}")
	public String getCheque(@PathVariable Long id) {
		Optional<Cheque> cheque = repository.findById(id);
		if (!cheque.isPresent()) {
			return "Cheque does not exist";
		}
		LOGGER.info("Cheque Found");
		return cheque.toString();
	}

	@DeleteMapping("/cheques/{id}")
	public void deleteCheque(@PathVariable Long id) {
		Optional<Cheque> cheque = repository.findById(id);
		if (!cheque.isPresent()) {
			LOGGER.info("Cheque does not exist");
		}
		else {
			repository.deleteById(id);
			LOGGER.info("Cheque deleted");
		}
	}
}
