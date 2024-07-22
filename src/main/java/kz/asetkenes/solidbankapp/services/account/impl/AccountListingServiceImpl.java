package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountListingServiceImpl implements AccountListingService {

    private final AccountDao accountDao;

    @Autowired
    public AccountListingServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account getClientAccount(String clientId, String accountId) {
        return accountDao.findByIdAndClientId(accountId, clientId);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientId, String accountId) {
        Account account = accountDao.findByIdAndClientId(accountId, clientId);

        if (account instanceof AccountWithdraw)
            return (AccountWithdraw) account;
        return null;
    }

    @Override
    public List<Account> getClientAccounts(String clientId) {
        return accountDao.findByClientId(clientId);
    }

    @Override
    public List<Account> getClientAccountByType(String clientId, AccountType accountType) {
        return accountDao.findByClientIdAndAccountType(clientId, accountType);
    }
}
