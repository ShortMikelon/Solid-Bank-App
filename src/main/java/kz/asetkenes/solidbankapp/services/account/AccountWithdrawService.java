package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.entities.AccountWithdraw;

public interface AccountWithdrawService {

    void withdraw(double amount, AccountWithdraw account);

}

