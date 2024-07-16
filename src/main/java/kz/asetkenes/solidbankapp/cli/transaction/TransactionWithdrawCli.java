package kz.asetkenes.solidbankapp.cli.transaction;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionWithdraw;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.exception.InsufficientFundsException;
import kz.asetkenes.solidbankapp.exception.NegativeAmountException;
import kz.asetkenes.solidbankapp.exception.WithdrawalNotAllowedException;
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

        double amount = withdrawDepositOperationCliUi.requestClientAmount();

        try {
            transactionWithdraw.execute(account, amount);

            System.out.println("Withdraw completed successfully");
        } catch (AccountNotFoundException ex) {
            System.out.println("Account not founded");
            System.out.println("Withdraw failed");
        } catch (NegativeAmountException ex) {
            System.out.println("Amount is negative");
            System.out.println("Withdraw failed");
        } catch (InsufficientFundsException ex) {
            System.out.println("The amount is more than the balance");
            System.out.println("Withdraw failed");
        } catch (WithdrawalNotAllowedException ex) {
            System.out.println("You cannot withdraw money from a fixed account");
            System.out.println("Withdraw failed");
        } catch (Exception ex) {
            System.out.println("Unknown error: " + ex.getMessage());
            System.out.println("Withdraw failed");
        }
    }

    public void withdrawMoney(String clientId, String accountId, double amount) {
        Account account = accountListingService.getClientAccount(clientId, accountId);

        transactionWithdraw.execute(account, amount);
    }
}
