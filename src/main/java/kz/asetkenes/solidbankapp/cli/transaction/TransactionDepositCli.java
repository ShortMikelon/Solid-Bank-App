package kz.asetkenes.solidbankapp.cli.transaction;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionDeposit;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.exception.NegativeAmountException;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import kz.asetkenes.solidbankapp.ui.transaction.WithdrawDepositOperationCliUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionDepositCli {

    private final TransactionDeposit transactionDeposit;

    private final WithdrawDepositOperationCliUi withdrawDepositOperationCliUi;

    private final AccountListingService accountListingService;

    @Autowired
    public TransactionDepositCli(
            TransactionDeposit transactionDeposit,
            WithdrawDepositOperationCliUi withdrawDepositOperationCliUi,
            AccountListingService accountListingService
    ) {
        this.transactionDeposit = transactionDeposit;
        this.withdrawDepositOperationCliUi = withdrawDepositOperationCliUi;
        this.accountListingService = accountListingService;
    }

    public void depositMoney(String clientId) throws IllegalArgumentException {
        String accountId = withdrawDepositOperationCliUi.requestClientAccountNumber();
        Account account = accountListingService.getClientAccount(clientId, accountId);

        double amount = withdrawDepositOperationCliUi.requestClientAmount();

        try {
            transactionDeposit.execute(account, amount);

            System.out.println("Deposit completed successfully");
        } catch (AccountNotFoundException ex) {
            System.out.println("Account not founded");
            System.out.println("Deposit failed");
        } catch (NegativeAmountException ex) {
            System.out.println("Amount is negative");
            System.out.println("Deposit failed");
        } catch (Exception ex) {
            System.out.println("Unknown error: " + ex.getMessage());
            System.out.println("Deposit failed");
        }
    }
}
