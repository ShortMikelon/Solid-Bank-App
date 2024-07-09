package kz.asetkenes.solidbankapp.services.impl;

import kz.asetkenes.solidbankapp.domain.entities.AccountType;

public interface AccountCreationService {

    void create(AccountType accountType, long bankId, String clientId, long accountId);

}

