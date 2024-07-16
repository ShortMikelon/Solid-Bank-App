package kz.asetkenes.solidbankapp.domain.transaction;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import kz.asetkenes.solidbankapp.domain.transaction.entities.TransactionType;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.exception.NegativeAmountException;
import kz.asetkenes.solidbankapp.services.account.AccountDepositService;
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

    public void execute(Account account, double amount) {
        if (account == null) throw new AccountNotFoundException("Account not founded");
        if (amount < 0) throw new NegativeAmountException("Amount is zero or negative");

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