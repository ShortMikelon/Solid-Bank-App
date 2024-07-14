package kz.asetkenes.solidbankapp.domain.account;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import kz.asetkenes.solidbankapp.services.account.impl.AccountCreationService;
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
        accountCreation.create(accountType, ID, clientId, this.lastAccountNumber);
        incrementLastAccountNumber();
    }

    private static final long ID = 1;

    private void incrementLastAccountNumber() {
        this.lastAccountNumber++;
    }
}
