package kz.asetkenes.solidbankapp.cli;

import kz.asetkenes.solidbankapp.domain.account.BankCore;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import kz.asetkenes.solidbankapp.services.account.impl.AccountListingService;
import kz.asetkenes.solidbankapp.ui.account.CreateAccountOperationUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountBasicCli {

    private final CreateAccountOperationUi createAccountOperationUi;

    private final BankCore bankCore;

    private final AccountListingService accountListing;

    @Autowired
    public AccountBasicCli(
            CreateAccountOperationUi createAccountOperationUi,
            BankCore bankCore,
            AccountListingService accountListing
    ) {
        this.createAccountOperationUi = createAccountOperationUi;
        this.bankCore = bankCore;
        this.accountListing = accountListing;
    }

    public void createAccountRequest(String clientId) {
        try {
            AccountType accountType = createAccountOperationUi.requestAccountType();
            bankCore.createNewAccount(accountType, clientId);

            System.out.println("Успешно");
        } catch (Exception ex) {
            System.out.println("Неуспешно");
        }
    }

    public void getAccounts(String clientId) {
        List<Account> accounts = accountListing.getClientAccounts(clientId);
        System.out.print("[");

        if (!accounts.isEmpty()) System.out.println();
        accounts.forEach(System.out::println);

        System.out.println("]");
    }

}

