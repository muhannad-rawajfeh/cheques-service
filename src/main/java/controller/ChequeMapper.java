package controller;

import objects.Account;
import objects.Cheque;

public class ChequeMapper {

    public static AccountEntity accountToEntity(Account account) {
        return new AccountEntity(account.getBankCode(), account.getBranchCode(), account.getAccountNumber());
    }

    public static ChequeEntity chequeToEntity(Cheque cheque){
        return new ChequeEntity(cheque.getAmount(), cheque.getNumber(), cheque.getDigit(),
                accountToEntity(cheque.getPayee()), accountToEntity(cheque.getDrawer()));
    }

    public static Account accountFromEntity(AccountEntity entity) {
        return new Account(entity.getBankCode(), entity.getBranchCode(), entity.getAccountNumber());
    }

    public static Cheque chequeFromEntity(ChequeEntity entity){
        return new Cheque(entity.getAmount(), entity.getNumber(), entity.getDigit(),
                accountFromEntity(entity.getPayee()), accountFromEntity(entity.getDrawer()));
    }


}
