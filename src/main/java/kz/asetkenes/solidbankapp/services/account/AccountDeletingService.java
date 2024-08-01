package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;

public interface AccountDeletingService {

    void delete(Account account);

    void deleteById(String id);
}
