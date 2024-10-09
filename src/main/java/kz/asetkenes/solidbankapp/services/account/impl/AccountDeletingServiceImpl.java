package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.data.account.AccountDao;
import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.services.account.AccountDeletingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDeletingServiceImpl implements AccountDeletingService {

    private final AccountDao accountDao;

    @Autowired
    public AccountDeletingServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void delete(Account account) {
        if (account == null) throw new AccountNotFoundException();
        if (!accountDao.existsById(account.getId())) throw new AccountNotFoundException();

        accountDao.delete(account);
    }

    @Override
    public void deleteById(String id) {
        if (!accountDao.existsById(id)) throw new AccountNotFoundException();

        accountDao.deleteById(id);
    }
}
