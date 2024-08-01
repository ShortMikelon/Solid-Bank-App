package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.*;
import kz.asetkenes.solidbankapp.services.account.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCreationServiceImpl implements AccountCreationService {

    private final AccountDao accountDao;

    @Autowired
    public AccountCreationServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account create(AccountType accountType, long bankId, String clientId, long accountId) {
        Account account;
        String id = String.format("%03d%06d", 1, accountId);

        if (accountType == AccountType.CHECKING) {
            account = new CheckingAccount(accountType, id, clientId, 0.0, true);
        } else if (accountType == AccountType.FIXED) {
            account = new FixedAccount(accountType, id, clientId, 0.0, false);
        } else {
            account = new SavingAccount(accountType, id, clientId, 0.0, true);
        }

        return accountDao.save(account);
    }
}
