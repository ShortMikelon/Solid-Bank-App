package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.services.account.AccountDepositService;
import org.springframework.stereotype.Service;

@Service
public class AccountDepositServiceImpl implements AccountDepositService {

    @Override
    public void deposit(AccountWithdraw account, double amount) {

    }
}
