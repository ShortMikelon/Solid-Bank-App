package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.entities.AccountWithdraw;

public interface AccountDepositService {
    void deposit(AccountWithdraw accountWithdraw, double amount);
}
