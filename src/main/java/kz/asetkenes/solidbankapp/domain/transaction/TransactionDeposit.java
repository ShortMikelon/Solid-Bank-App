package kz.asetkenes.solidbankapp.domain.transaction;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import kz.asetkenes.solidbankapp.domain.transaction.entities.TransactionType;
import kz.asetkenes.solidbankapp.services.account.impl.AccountDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TransactionDeposit {

    private final TransactionDao transactionDao;

    private final AccountDepositService accountDepositService;

    @Autowired
    public TransactionDeposit(AccountDepositService accountDepositService, TransactionDao transactionDao) {
        this.accountDepositService = accountDepositService;
        this.transactionDao = transactionDao;
    }

    public void execute(AccountWithdraw account, double amount) {
        accountDepositService.deposit(account, amount);

        Transaction newTransaction = new Transaction(
                TransactionType.DEPOSIT,
                account.getId(),
                account.getClientId(),
                amount,
                System.currentTimeMillis()
        );

        transactionDao.addTransaction(newTransaction);
    }
}

