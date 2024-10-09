package kz.asetkenes.solidbankapp.services.account;

import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.domain.account.model.AccountType;

public interface AccountCreationService {

    Account create(AccountType accountType, long bankId, String clientId, long accountId);

}

