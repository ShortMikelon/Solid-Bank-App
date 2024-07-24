package kz.asetkenes.solidbankapp.cli.transaction;

import jakarta.transaction.Transactional;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionDeposit;
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
public class TransferCli {

    private final TransactionDeposit deposit;

    private final TransactionWithdraw withdraw;

    private final WithdrawDepositOperationCliUi withdrawDepositOperationCliUi;

    private final AccountListingService accountListingService;

    @Autowired
    public TransferCli(
            TransactionDeposit deposit,
            TransactionWithdraw withdraw,
            WithdrawDepositOperationCliUi withdrawDepositOperationCliUi,
            AccountListingService accountListingService
    ) {
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.withdrawDepositOperationCliUi = withdrawDepositOperationCliUi;
        this.accountListingService = accountListingService;
    }

    @Transactional
    public void transfer(String clientId) {
        System.out.println("The account from which it is withdrawn");
        String withdrawAccountId = withdrawDepositOperationCliUi.requestClientAccountNumber();
        Account withdrawAccount = this.accountListingService.getClientAccount(clientId, withdrawAccountId);

        System.out.println("Account on which to deposit");
        String depositAccountId = withdrawDepositOperationCliUi.requestClientAccountNumber();
        Account depositAccount = this.accountListingService.getClientAccount(clientId, depositAccountId);

        double amount = withdrawDepositOperationCliUi.requestClientAmount();

        try {
            withdraw.execute(withdrawAccount, amount);

            deposit.execute(depositAccount, amount);

            System.out.println("transfer completed successfully");
        } catch (AccountNotFoundException ex) {
            System.out.println("Account not founded");
            System.out.println("Transfer failed");
        } catch (NegativeAmountException ex) {
            System.out.println("Amount is negative");
            System.out.println("Transfer failed");
        } catch (InsufficientFundsException ex) {
            System.out.println("The amount is more than the balance");
            System.out.println("Transfer failed");
        } catch (WithdrawalNotAllowedException ex) {
            System.out.println("You cannot withdraw money from a fixed account");
            System.out.println("Transfer failed");
        } catch (Exception ex) {
            System.out.println("Unknown error: " + ex.getMessage());
            System.out.println("Transfer failed");
        }
    }
}
