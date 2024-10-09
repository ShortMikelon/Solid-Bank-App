package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.account.model.Account;

public interface AccountDepositService {
    void deposit(Account accountWithdraw, double amount);
}
