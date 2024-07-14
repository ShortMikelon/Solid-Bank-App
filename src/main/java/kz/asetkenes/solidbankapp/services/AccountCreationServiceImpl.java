package kz.asetkenes.solidbankapp.services;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.*;
import kz.asetkenes.solidbankapp.domain.entities.*;
import kz.asetkenes.solidbankapp.services.account.impl.AccountCreationService;
import org.springframework.stereotype.Service;

@Service
public class AccountCreationServiceImpl implements AccountCreationService {

    private final AccountDao accountDao;

    public AccountCreationServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void create(AccountType accountType, long bankId, String clientId, long accountId) {
        Account account;
        String id = String.format("%03d%06d", 1, accountId);

        if (accountType == AccountType.CHECKING) {
            account = new CheckingAccount(accountType, id, clientId, 0.0, true);
        } else if (accountType == AccountType.FIXED) {
            account = new FixedAccount(accountType, id, clientId, 0.0, false);
        } else {
            account = new SavingAccount(accountType, id, clientId, 0.0, true);
        }
        
        accountDao.createNewAccount(account);
    }
}
