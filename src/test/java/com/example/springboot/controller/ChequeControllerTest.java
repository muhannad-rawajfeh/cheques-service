package com.example.springboot.controller;

import com.example.springboot.objects.Account;
import com.example.springboot.objects.Cheque;
import com.example.springboot.repository.ChequeEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ChequeControllerTest extends AbstractTest {

    @Test
    void createCheque() throws Exception {

        String uri = "/cheques";
        Cheque cheque = new Cheque(new BigDecimal("1000"), "123", "01",
                new Account("12", "1234", "12345678"),
                new Account("21", "4321", "87654321"));

        String inputJson = mapToJson(cheque);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Cheque created successfully");
    }

    @Test
    void updateCheque() throws Exception {

        Mockito.when(chequeRepository.findById(1L)).thenReturn(Optional.of(new ChequeEntity()));

        String uri = "/cheques/1";
        Cheque cheque = new Cheque(new BigDecimal("1000"), "123", "01",
                new Account("12", "1234", "12345678"),
                new Account("21", "4321", "87654321"));

        String inputJson = mapToJson(cheque);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Cheque updated successfully");
    }

    @Test
    void listCheques() throws Exception {

        ChequeEntity chequeEntity = new ChequeEntity();
        chequeEntity.setId(2L);
        Pageable pageable = PageRequest.of(3, 8);
        Mockito.when(chequeRepository.findAll(Example.of(chequeEntity), pageable))
                .thenReturn(new PageImpl<>(Collections.singletonList(chequeEntity)));

        String uri = "/cheques";
        mvc.perform(MockMvcRequestBuilders.get(uri)
                        .param("id", "2")
                        .param("page", "3")
                        .param("size", "8"))
                .andExpect(status().isOk());

        ArgumentCaptor<Example<ChequeEntity>> exampleCaptor = ArgumentCaptor.forClass(Example.class);
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        verify(chequeRepository).findAll(exampleCaptor.capture(), pageableCaptor.capture());
        assertEquals(2L, exampleCaptor.getValue().getProbe().getId());
        Pageable pageableValue = pageableCaptor.getValue();
        assertEquals(3, pageableValue.getPageNumber());
        assertEquals(8, pageableValue.getPageSize());
        assertEquals(Sort.unsorted(), pageableValue.getSort());
    }

    @Test
    void getCheque() throws Exception {

        Mockito.when(chequeRepository.findById(1L)).thenReturn(Optional.of(new ChequeEntity()));

        String uri = "/cheques/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        ChequeEntity cheque = mapFromJson(content, ChequeEntity.class);

        assertNotNull(cheque);
    }

    @Test
    void deleteCheque() throws Exception {

        Mockito.when(chequeRepository.findById(1L)).thenReturn(Optional.of(new ChequeEntity()));

        String uri = "/cheques/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Cheque deleted successfully");
    }
}
