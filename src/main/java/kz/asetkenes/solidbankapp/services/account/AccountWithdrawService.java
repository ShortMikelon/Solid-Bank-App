package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;

public interface AccountWithdrawService {

    void withdraw(double amount, Account account);

}

