package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.services.account.AccountDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDepositServiceImpl implements AccountDepositService {

    private final AccountDao accountDao;

    @Autowired
    public AccountDepositServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void deposit(Account account, double amount) {
        double newBalance = account.getBalance() + amount;

        account.setBalance(newBalance);
        accountDao.save(account);
    }
}
