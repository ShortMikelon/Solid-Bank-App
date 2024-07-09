package kz.asetkenes.solidbankapp.data;

import kz.asetkenes.solidbankapp.domain.entities.Account;
import kz.asetkenes.solidbankapp.domain.entities.AccountType;
import kz.asetkenes.solidbankapp.domain.entities.AccountWithdraw;

import java.util.List;

public interface AccountDao {

    void createNewAccount(Account account);

    void updateAccount(Account account);

    List<Account> getClientAccounts(String clientId);

    List<Account> getClientAccountsByType(String clientId, AccountType accountType);

    AccountWithdraw getClientWithdrawAccount(String clientId, String accountId);

    Account getClientAccount(String clientId, String accountId);

}

