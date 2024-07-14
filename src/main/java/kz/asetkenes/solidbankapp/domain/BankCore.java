package kz.asetkenes.solidbankapp.domain;

import kz.asetkenes.solidbankapp.domain.entities.AccountType;
import kz.asetkenes.solidbankapp.services.impl.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankCore {

    private long lastAccountNumber = 1;

    private final AccountCreationService accountCreation;

    @Autowired
    public BankCore(AccountCreationService accountCreation) {
        this.accountCreation = accountCreation;
    }

    public void createNewAccount(AccountType accountType, String clientId) {
        accountCreation.create(accountType, id, clientId, this.lastAccountNumber);
        incrementLastAccountNumber();
    }

    private static long id = 1;

    private void incrementLastAccountNumber() {
        this.lastAccountNumber++;
    }
}
