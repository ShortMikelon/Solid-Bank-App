package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;

public interface AccountDepositService {
    void deposit(AccountWithdraw accountWithdraw, double amount);
}
