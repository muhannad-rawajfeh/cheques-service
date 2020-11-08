package com.example.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChequeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void createCheque() {
    }

    @Test
    void updateCheque() {
    }

    @Test
    void listCheques() {
    }

    @Test
    void getCheque() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cheques/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"amount\": null,\n" +
                        "    \"number\": null,\n" +
                        "    \"digit\": null,\n" +
                        "    \"payee\": null,\n" +
                        "    \"drawer\": null\n" +
                        "}"));
    }

    @Test
    void deleteCheque() {
    }
}