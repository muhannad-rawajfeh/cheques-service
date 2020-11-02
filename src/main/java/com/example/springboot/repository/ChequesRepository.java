package com.example.springboot.repository;

import org.springframework.data.repository.CrudRepository;

public interface ChequesRepository extends CrudRepository<ChequeEntity, Long> {
}
