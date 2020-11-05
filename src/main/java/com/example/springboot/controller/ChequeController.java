package com.example.springboot.controller;

import com.example.springboot.objects.Cheque;
import com.example.springboot.repository.AccountEntity;
import com.example.springboot.repository.ChequeEntity;
import com.example.springboot.repository.ChequeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class ChequeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChequeController.class);

	private final ChequeRepository repository;

	public ChequeController(ChequeRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/cheques")
	public ChequeEntity createCheque(@RequestBody Cheque cheque) {
		LOGGER.info("Cheque Created");
		return repository.save(Mapper.chequeToEntity(cheque));
	}

	@PostMapping("/cheques/{id}")
	public void updateCheque(@PathVariable Long id, @RequestBody Cheque cheque) {
		Optional<ChequeEntity> chequeEntity = repository.findById(id);
		if (!chequeEntity.isPresent()) {
			LOGGER.info("Cheque does not exist");
		}
		else {
			repository.save(Mapper.chequeToEntity(cheque, id));
		}
	}

	@GetMapping("/cheques")
	public Page<ChequeEntity> listCheques(ChequeEntity cheque,
										  @RequestParam Optional<Integer> page,
										  @RequestParam Optional<String> sortBy) {
		LOGGER.info("Cheques List");
		List<ChequeEntity> chequesList = repository.findAll(Example.of(cheque));
		return new PageImpl<>(chequesList, PageRequest.of(page.orElse(0), 5), chequesList.size());
	}

	@GetMapping("/cheques/{id}")
	public Optional<ChequeEntity> getCheque(@PathVariable Long id) {
		Optional<ChequeEntity> cheque = repository.findById(id);
		if (!cheque.isPresent()) {
			LOGGER.info("Cheque does not exist");
		}
		else {
			LOGGER.info("Cheque Found");
		}
		return cheque;
	}

	@DeleteMapping("/cheques/{id}")
	public void deleteCheque(@PathVariable Long id) {
		Optional<ChequeEntity> cheque = repository.findById(id);
		if (!cheque.isPresent()) {
			LOGGER.info("Cheque does not exist");
		}
		else {
			LOGGER.info("Cheque deleted");
			repository.deleteById(id);
		}
	}
}
