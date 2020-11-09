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
	public String createCheque(@RequestBody Cheque cheque) {
		LOGGER.info("Create cheque request");
		repository.save(Mapper.chequeToEntity(cheque));
		return "Cheque created successfully";
	}

	@PutMapping("/cheques/{id}")
	public String updateCheque(@PathVariable Long id, @RequestBody Cheque cheque) {
		LOGGER.info("Update cheque request");
		repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
		repository.save(Mapper.chequeToEntity(cheque, id));
		return "Cheque updated successfully";
	}

	@GetMapping("/cheques")
	public Page<ChequeEntity> listCheques(ChequeEntity cheque,
										  @RequestParam Optional<Integer> page,
										  @RequestParam Optional<String> sortBy) {
		LOGGER.info("List cheques request");
		List<ChequeEntity> chequesList = repository.findAll(Example.of(cheque));
		return new PageImpl<>(chequesList, PageRequest.of(page.orElse(0), 5,
				Sort.Direction.ASC, sortBy.orElse("id")), chequesList.size());
	}

	@GetMapping("/cheques/{id}")
	public ChequeEntity getCheque(@PathVariable Long id) {
		LOGGER.info("Get cheque request");
		return repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
	}

	@DeleteMapping("/cheques/{id}")
	public String deleteCheque(@PathVariable Long id) {
		LOGGER.info("Delete cheque request");
		repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
		repository.deleteById(id);
		return "Cheque deleted successfully";
	}
}
