package com.example.springboot.controller;

import com.example.springboot.objects.Account;
import com.example.springboot.objects.Cheque;
import com.example.springboot.repository.ChequeEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void updateCheque() throws Exception {

        Mockito.when(chequeRepository.findById(1L)).thenReturn(Optional.of(new ChequeEntity()));

        String uri = "/cheques/1";
        Cheque cheque = new Cheque(new BigDecimal("1000"), "123", "01",
                new Account("11", "22", "33"),
                new Account("44", "55", "66"));

        String inputJson = mapToJson(cheque);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Cheque updated successfully");
    }

    @Test
    void listCheques() throws Exception {

//        String uri = "/cheques";
//        mvc.perform(MockMvcRequestBuilders.get(uri)
//                .param("page", "0")
//                .param("sortBy", "number")
//                .param("amount", ""))
//                .andExpect(status().isOk());
//
//        ArgumentCaptor<Pageable> pageableCaptor =
//                ArgumentCaptor.forClass(Pageable.class);
//        verify(chequeRepository).findAll(pageableCaptor.capture());
//        PageRequest pageable = (PageRequest) pageableCaptor.getValue();
//
//        assertThat(pageable).hasPageNumber(5);
//        assertThat(pageable).hasPageSize(10);
//        assertThat(pageable).hasSort("name", Sort.Direction.ASC);
//        assertThat(pageable).hasSort("id", Sort.Direction.DESC);
    }

    @Test
    void getCheque() throws Exception {

        Mockito.when(chequeRepository.findById(1L)).thenReturn(Optional.of(new ChequeEntity()));

        String uri = "/cheques/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

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
