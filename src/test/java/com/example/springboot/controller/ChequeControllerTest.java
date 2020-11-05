package com.example.springboot.controller;

import com.example.springboot.repository.ChequeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ChequeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void createCheque() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/cheques");
        MvcResult result = mvc.perform(request).andReturn();
        Assertions.assertTrue(result instanceof ChequeEntity);
    }

    @Test
    void updateCheque() {
    }

    @Test
    void listCheques() {
    }

    @Test
    void getCheque() {
    }

    @Test
    void deleteCheque() {
    }
}