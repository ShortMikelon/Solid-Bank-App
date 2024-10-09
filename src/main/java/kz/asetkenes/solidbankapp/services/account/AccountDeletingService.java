package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.account.model.Account;

public interface AccountDeletingService {

    void delete(Account account);

    void deleteById(String id);
}
