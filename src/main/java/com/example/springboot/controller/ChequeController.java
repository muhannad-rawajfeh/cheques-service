package com.example.springboot.controller;

import com.example.springboot.exceptions.ChequeNotFoundException;
import com.example.springboot.objects.Cheque;
import com.example.springboot.repository.ChequeEntity;
import com.example.springboot.repository.ChequeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ChequeController {

	private static final Logger log = LoggerFactory.getLogger(ChequeController.class);

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	private final ChequeRepository repository;

	public ChequeController(ChequeRepository repository) {
		this.repository = repository;
	}

	private ResponseEntity getResponseEntity(Set<ConstraintViolation<Cheque>> violations) {
		List<String> violationsList = new ArrayList<>();
		for (ConstraintViolation<Cheque> violation : violations) {
			log.error(violation.getMessage());
			violationsList.add(violation.getMessage());
		}
		return ResponseEntity.badRequest()
				.body("Please enter valid fields, the following errors occurred:\n" + violationsList);
	}

	@PostMapping("/cheques")
	public ResponseEntity createCheque(@RequestBody Cheque cheque) {
		log.info("Create cheque request");
		Set<ConstraintViolation<Cheque>> violations = validator.validate(cheque);
		if (violations.isEmpty()) {
			repository.save(Mapper.chequeToEntity(cheque));
			return ResponseEntity.ok("Cheque created successfully");
		}
		return getResponseEntity(violations);
	}

	@PutMapping("/cheques/{id}")
	public ResponseEntity updateCheque(@PathVariable Long id, @RequestBody Cheque cheque) {
		log.info("Update cheque request");
		repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
		Set<ConstraintViolation<Cheque>> violations = validator.validate(cheque);
		if (violations.isEmpty()) {
			repository.save(Mapper.chequeToEntity(cheque, id));
			return ResponseEntity.ok("Cheque updated successfully");
		}
		return getResponseEntity(violations);
	}

	@GetMapping("/cheques")
	public Page<ChequeEntity> listCheques(ChequeEntity cheque,
										  @RequestParam Optional<Integer> page,
										  @RequestParam Optional<String> sortBy) {
		log.info("List cheques request");
		List<ChequeEntity> chequesList = repository.findAll(Example.of(cheque));
		return new PageImpl<>(chequesList, PageRequest.of(page.orElse(0), 5,
				Sort.Direction.ASC, sortBy.orElse("id")), chequesList.size());
	}

	@GetMapping("/cheques/{id}")
	public ChequeEntity getCheque(@PathVariable Long id) {
		log.info("Get cheque request");
		return repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
	}

	@DeleteMapping("/cheques/{id}")
	public String deleteCheque(@PathVariable Long id) {
		log.info("Delete cheque request");
		repository.findById(id)
				.orElseThrow(() -> new ChequeNotFoundException(id));
		repository.deleteById(id);
		return "Cheque deleted successfully";
	}
}
