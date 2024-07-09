package kz.asetkenes.solidbankapp.services.impl;

import kz.asetkenes.solidbankapp.domain.entities.Account;
import kz.asetkenes.solidbankapp.domain.entities.AccountType;
import kz.asetkenes.solidbankapp.domain.entities.AccountWithdraw;

import java.util.List;

public interface AccountListingService {

    Account getClientAccount(String clientId, String accountId);

    AccountWithdraw getClientWithdrawAccount(String clientId, String accountId);

    List<Account> getClientAccounts(String clientId);

    List<Account> getClientAccountByType(String clientId, AccountType accountType);

}

