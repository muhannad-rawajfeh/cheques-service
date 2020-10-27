package com.example.springboot;

import org.springframework.data.repository.CrudRepository;

public interface ChequesRepository extends CrudRepository<Cheque, Long> {
}
