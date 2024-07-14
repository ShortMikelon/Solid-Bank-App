package kz.asetkenes.solidbankapp.cli.transaction;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionWithdraw;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import kz.asetkenes.solidbankapp.ui.transaction.WithdrawDepositOperationCliUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionWithdrawCli {

    private final TransactionWithdraw transactionWithdraw;

    private final WithdrawDepositOperationCliUi withdrawDepositOperationCliUi;

    private final AccountListingService accountListingService;

    @Autowired
    public TransactionWithdrawCli(
            TransactionWithdraw transactionWithdraw,
            WithdrawDepositOperationCliUi withdrawDepositOperationCliUi,
            AccountListingService accountListingService
    ) {
        this.transactionWithdraw = transactionWithdraw;
        this.withdrawDepositOperationCliUi = withdrawDepositOperationCliUi;
        this.accountListingService = accountListingService;
    }

    public void withdrawMoney(String clientId) {
        String accountId = withdrawDepositOperationCliUi.requestClientAccountNumber();
        Account account = accountListingService.getClientAccount(clientId, accountId);

        if (account == null) {
            System.out.println("Account not founded");
            return;
        }

        if (!account.isWithdrawAllowed()) {
            System.out.println("Unable to withdraw money from savings account");
            return;
        }

        double amount = withdrawDepositOperationCliUi.requestClientAmount();

        if (amount <= 0) {
            System.out.println("Amount is zero or negative");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Amount is more than the balance ");
            return;
        }

        transactionWithdraw.execute(account, amount);
    }
}
