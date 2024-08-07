package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.domain.account.model.AccountType;
import kz.asetkenes.solidbankapp.domain.account.model.AccountWithdraw;

import java.util.List;

public interface AccountListingService {

    Account getClientAccount(String clientId, String accountId);

    AccountWithdraw getClientWithdrawAccount(String clientId, String accountId);

    List<Account> getClientAccounts(String clientId);

    List<Account> getClientAccountByType(String clientId, AccountType accountType);

}

