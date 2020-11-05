package com.example.springboot.controller;

import com.example.springboot.objects.Account;
import com.example.springboot.objects.Cheque;
import com.example.springboot.repository.AccountEntity;
import com.example.springboot.repository.ChequeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MapperTest {

    private final BigDecimal AMOUNT = new BigDecimal("100.00");
    private final String NUMBER = "0001";
    private final String DIGIT = "01";

    private final String BANK_CODE = "11";
    private final String BRANCH_CODE = "22";
    private final String ACCOUNT_NUMBER = "33";

    private final String PAYEE_BANK_CODE = "11";
    private final String PAYEE_BRANCH_CODE = "22";
    private final String PAYEE_ACCOUNT_NUMBER = "33";

    private final String DRAWER_BANK_CODE = "44";
    private final String DRAWER_BRANCH_CODE = "55";
    private final String DRAWER_ACCOUNT_NUMBER = "66";

    @Test
    void givenAccount_whenMap_thenShouldReturnAccountEntity() {

        Account account = new Account(BANK_CODE, BRANCH_CODE, ACCOUNT_NUMBER);
        AccountEntity accountEntity = Mapper.accountToEntity(account);

        Assertions.assertEquals(BANK_CODE, accountEntity.getBankCode());
        Assertions.assertEquals(BRANCH_CODE, accountEntity.getBranchCode());
        Assertions.assertEquals(ACCOUNT_NUMBER, accountEntity.getAccountNumber());
    }

    @Test
    void givenAccountEntity_whenMap_thenShouldReturnAccount() {

        AccountEntity accountEntity = new AccountEntity(BANK_CODE, BRANCH_CODE, ACCOUNT_NUMBER);
        Account account = Mapper.accountFromEntity(accountEntity);

        Assertions.assertEquals(BANK_CODE, account.getBankCode());
        Assertions.assertEquals(BRANCH_CODE, account.getBranchCode());
        Assertions.assertEquals(ACCOUNT_NUMBER, account.getAccountNumber());
    }

    @Test
    void givenCheque_whenMap_thenShouldReturnChequeEntity() {

        Account payee = new Account(PAYEE_BANK_CODE, PAYEE_BRANCH_CODE, PAYEE_ACCOUNT_NUMBER);
        Account drawer = new Account(DRAWER_BANK_CODE, DRAWER_BRANCH_CODE, DRAWER_ACCOUNT_NUMBER);

        Cheque cheque = new Cheque(AMOUNT, NUMBER, DIGIT, payee, drawer);
        ChequeEntity chequeEntity = Mapper.chequeToEntity(cheque);

        Assertions.assertEquals(AMOUNT, chequeEntity.getAmount());
        Assertions.assertEquals(DIGIT, chequeEntity.getDigit());
        Assertions.assertEquals(NUMBER, chequeEntity.getNumber());

        Assertions.assertEquals(PAYEE_BANK_CODE, chequeEntity.getPayee().getBankCode());
        Assertions.assertEquals(PAYEE_BRANCH_CODE, chequeEntity.getPayee().getBranchCode());
        Assertions.assertEquals(PAYEE_ACCOUNT_NUMBER, chequeEntity.getPayee().getAccountNumber());

        Assertions.assertEquals(DRAWER_BANK_CODE, chequeEntity.getDrawer().getBankCode());
        Assertions.assertEquals(DRAWER_BRANCH_CODE, chequeEntity.getDrawer().getBranchCode());
        Assertions.assertEquals(DRAWER_ACCOUNT_NUMBER, chequeEntity.getDrawer().getAccountNumber());
    }

    @Test
    void givenChequeEntity_whenMap_thenShouldReturnCheque() {

        AccountEntity payeeEntity = new AccountEntity(PAYEE_BANK_CODE, PAYEE_BRANCH_CODE, PAYEE_ACCOUNT_NUMBER);
        AccountEntity drawerEntity = new AccountEntity(DRAWER_BANK_CODE, DRAWER_BRANCH_CODE, DRAWER_ACCOUNT_NUMBER);

        ChequeEntity chequeEntity = new ChequeEntity(AMOUNT, NUMBER, DIGIT, payeeEntity, drawerEntity);
        Cheque cheque = Mapper.chequeFromEntity(chequeEntity);

        Assertions.assertEquals(AMOUNT, cheque.getAmount());
        Assertions.assertEquals(DIGIT, cheque.getDigit());
        Assertions.assertEquals(NUMBER, cheque.getNumber());

        Assertions.assertEquals(PAYEE_BANK_CODE, cheque.getPayee().getBankCode());
        Assertions.assertEquals(PAYEE_BRANCH_CODE, cheque.getPayee().getBranchCode());
        Assertions.assertEquals(PAYEE_ACCOUNT_NUMBER, cheque.getPayee().getAccountNumber());

        Assertions.assertEquals(DRAWER_BANK_CODE, cheque.getDrawer().getBankCode());
        Assertions.assertEquals(DRAWER_BRANCH_CODE, cheque.getDrawer().getBranchCode());
        Assertions.assertEquals(DRAWER_ACCOUNT_NUMBER, cheque.getDrawer().getAccountNumber());
    }

    @Test
    void givenChequeWithId_whenMap_thenShouldReturnChequeEntity_withCertainId() {

        Account payee = new Account(PAYEE_BANK_CODE, PAYEE_BRANCH_CODE, PAYEE_ACCOUNT_NUMBER);
        Account drawer = new Account(DRAWER_BANK_CODE, DRAWER_BRANCH_CODE, DRAWER_ACCOUNT_NUMBER);

        Cheque cheque = new Cheque(AMOUNT, NUMBER, DIGIT, payee, drawer);
        final Long id = 1L;
        ChequeEntity chequeEntity = Mapper.chequeToEntity(cheque, id);

        Assertions.assertEquals(id, chequeEntity.getId());
        Assertions.assertEquals(AMOUNT, chequeEntity.getAmount());
        Assertions.assertEquals(DIGIT, chequeEntity.getDigit());
        Assertions.assertEquals(NUMBER, chequeEntity.getNumber());

        Assertions.assertEquals(PAYEE_BANK_CODE, chequeEntity.getPayee().getBankCode());
        Assertions.assertEquals(PAYEE_BRANCH_CODE, chequeEntity.getPayee().getBranchCode());
        Assertions.assertEquals(PAYEE_ACCOUNT_NUMBER, chequeEntity.getPayee().getAccountNumber());

        Assertions.assertEquals(DRAWER_BANK_CODE, chequeEntity.getDrawer().getBankCode());
        Assertions.assertEquals(DRAWER_BRANCH_CODE, chequeEntity.getDrawer().getBranchCode());
        Assertions.assertEquals(DRAWER_ACCOUNT_NUMBER, chequeEntity.getDrawer().getAccountNumber());
    }
}
