package kz.asetkenes.solidbankapp.services;

import kz.asetkenes.solidbankapp.data.AccountDao;
import kz.asetkenes.solidbankapp.domain.entities.Account;
import kz.asetkenes.solidbankapp.domain.entities.AccountType;
import kz.asetkenes.solidbankapp.domain.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.services.impl.AccountListingService;
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
        return accountDao.getClientAccount(clientId, accountId);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientId, String accountId) {
        return accountDao.getClientWithdrawAccount(clientId, accountId);
    }

    @Override
    public List<Account> getClientAccounts(String clientId) {
        return accountDao.getClientAccounts(clientId);
    }

    @Override
    public List<Account> getClientAccountByType(String clientId, AccountType accountType) {
        return accountDao.getClientAccountsByType(clientId, accountType);
    }
}
