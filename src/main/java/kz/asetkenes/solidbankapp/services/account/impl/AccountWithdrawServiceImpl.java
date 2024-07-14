package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {

    private final AccountDao accountDao;

    @Autowired
    public AccountWithdrawServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void withdraw(double amount, AccountWithdraw account) {
        double balance = account.getBalance();

        double newBalance = balance - amount;
        account.setBalance(newBalance);

        accountDao.updateAccount(account);
    }
}
