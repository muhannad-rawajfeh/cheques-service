package com.example.springboot.controller;

import com.example.springboot.exceptions.ChequeNotFoundException;
import com.example.springboot.objects.Cheque;
import com.example.springboot.repository.ChequeEntity;
import com.example.springboot.repository.ChequeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

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

	@PutMapping("/cheques/{id}")
	public ChequeEntity updateCheque(@PathVariable Long id, @RequestBody Cheque cheque) {
		repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
		LOGGER.info("Cheque Updated");
		return repository.save(Mapper.chequeToEntity(cheque, id));
	}

	@GetMapping("/cheques")
	public Page<ChequeEntity> listCheques(ChequeEntity cheque,
										  @RequestParam Optional<Integer> page,
										  @RequestParam Optional<String> sortBy) {
		LOGGER.info("Cheques List");
		List<ChequeEntity> chequesList = repository.findAll(Example.of(cheque));
		return new PageImpl<>(chequesList, PageRequest.of(page.orElse(0), 5,
				Sort.Direction.ASC, sortBy.orElse("id")), chequesList.size());
	}

	@GetMapping("/cheques/{id}")
	public ChequeEntity getCheque(@PathVariable Long id) {
		LOGGER.info("Cheque Requested");
		return repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
	}

	@DeleteMapping("/cheques/{id}")
	public void deleteCheque(@PathVariable Long id) {
		repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
		LOGGER.info("Cheque Deleted");
		repository.deleteById(id);
	}
}
