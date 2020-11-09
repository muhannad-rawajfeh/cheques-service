package com.example.springboot.controller;

import com.example.springboot.objects.Account;
import com.example.springboot.objects.Cheque;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChequeControllerTest extends AbstractTest {

    @Test
    void createCheque() throws Exception {

        String uri = "/cheques";

        Cheque cheque = new Cheque(new BigDecimal("1000"), "123", "01",
                new Account("11", "22", "33"),
                new Account("44", "55", "66"));

        String inputJson = mapToJson(cheque);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Cheque created successfully");
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
