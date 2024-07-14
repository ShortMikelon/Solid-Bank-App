package kz.asetkenes.solidbankapp.services.account.impl;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;

public interface AccountCreationService {

    void create(AccountType accountType, long bankId, String clientId, long accountId);

}

