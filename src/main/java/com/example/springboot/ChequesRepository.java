package com.example.springboot;

import controller.ChequeEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChequesRepository extends CrudRepository<ChequeEntity, Long> {
}
