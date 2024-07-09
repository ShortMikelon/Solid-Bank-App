package kz.asetkenes.solidbankapp.ui;

import kz.asetkenes.solidbankapp.domain.BankCore;
import kz.asetkenes.solidbankapp.domain.entities.Account;
import kz.asetkenes.solidbankapp.domain.entities.AccountType;
import kz.asetkenes.solidbankapp.services.impl.AccountListingService;

import java.util.List;

public class AccountBasicCli {

    private final CreateAccountOperationUi createAccountOperationUi;

    private final BankCore bankCore;

    private final AccountListingService accountListing;

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

