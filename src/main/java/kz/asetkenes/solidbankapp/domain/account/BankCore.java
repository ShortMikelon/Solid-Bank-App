package kz.asetkenes.solidbankapp.domain.account;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import kz.asetkenes.solidbankapp.exception.InvalidAccountTypeException;
import kz.asetkenes.solidbankapp.services.account.AccountCreationService;
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

    public Account execute(String accountType, String clientId) {
        AccountType _accountType = getAccountTypeByString(accountType);

        Account newAccount = accountCreation.create(_accountType, ID, clientId, this.lastAccountNumber);
        incrementLastAccountNumber();

        return newAccount;
    }

    private AccountType getAccountTypeByString(String accountTypeStr) {
        try {
            String _accountTypeStr = accountTypeStr.toUpperCase();
            return AccountType.valueOf(_accountTypeStr);
        } catch (IllegalArgumentException ex) {
            throw new InvalidAccountTypeException();
        }
    }

    private static final long ID = 1;

    private void incrementLastAccountNumber() {
        this.lastAccountNumber++;
    }
}
