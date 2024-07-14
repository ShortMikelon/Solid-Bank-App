package kz.asetkenes.solidbankapp.cli.transaction;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionDeposit;
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

    public void depositMoney(String clientId) {
        String accountId = withdrawDepositOperationCliUi.requestClientAccountNumber();
        AccountWithdraw account = accountListingService.getClientWithdrawAccount(clientId, accountId);

        double amount = withdrawDepositOperationCliUi.requestClientAmount();
        transactionDeposit.execute(account, amount);
    }
}
