package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;

public interface AccountWithdrawService {

    void withdraw(double amount, AccountWithdraw account);

}

