package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountListingServiceImpl implements AccountListingService {

    private final AccountDao accountDao;

    @Autowired
    public AccountListingServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account getClientAccount(String clientId, String accountId) {
        Optional<Account> result = accountDao.findById(accountId);

        if (result.isEmpty()) throw new AccountNotFoundException();

        return result.get();
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientId, String accountId) {
        Optional<Account> result = accountDao.findById(accountId);

        if (result.isEmpty()) throw new AccountNotFoundException();

        Account account = result.get();

        if (account instanceof AccountWithdraw)
            return (AccountWithdraw) account;

        throw new AccountNotFoundException();
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
