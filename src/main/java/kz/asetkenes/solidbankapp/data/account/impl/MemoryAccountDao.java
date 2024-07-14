package kz.asetkenes.solidbankapp.data.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryAccountDao implements AccountDao {

    private final List<Account> accountList = new ArrayList<>();

    @Override
    public void createNewAccount(Account account) {
        if (accountList.contains(account)) return;
        accountList.add(account);
    }

    @Override
    public void updateAccount(Account account) {
        int index = accountList.indexOf(account);
        accountList.set(index, account);
    }

    @Override
    public List<Account> getClientAccounts(String clientId) {
        return accountList
                .stream()
                .filter(item->item.getClientId().equals(clientId))
                .toList();
    }

    @Override
    public List<Account> getClientAccountsByType(String clientId, AccountType accountType) {
        return accountList
                .stream()
                .filter(item->item.getClientId().equals(clientId) && item.getAccountType().equals(accountType))
                .toList();
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientId, String accountId) {
        Optional<Account> account = accountList
                .stream()
                .filter(item->item instanceof AccountWithdraw)
                .filter(item->item.getClientId().equals(clientId) && item.getId().equals(accountId))
                .findFirst();

        return (AccountWithdraw) account.orElse(null);
    }

    @Override
    public Account getClientAccount(String clientId, String accountId) {
        Optional<Account> account = accountList
                .stream()
                .filter(item->item.getClientId().equals(clientId) && item.getId().equals(accountId))
                .findFirst();

        return account.orElse(null);
    }

}
