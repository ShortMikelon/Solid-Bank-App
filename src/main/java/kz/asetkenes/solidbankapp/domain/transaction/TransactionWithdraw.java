package kz.asetkenes.solidbankapp.domain.transaction;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountWithdraw;
import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import kz.asetkenes.solidbankapp.domain.transaction.entities.TransactionType;
import kz.asetkenes.solidbankapp.services.account.AccountWithdrawService;
import org.springframework.stereotype.Component;

@Component
public class TransactionWithdraw {

    private final AccountWithdrawService accountWithdrawService;

    private final TransactionDao transactionDao;

    public TransactionWithdraw(
            AccountWithdrawService accountWithdrawService,
            TransactionDao transactionDao
    ) {
        this.accountWithdrawService = accountWithdrawService;
        this.transactionDao = transactionDao;
    }

    public void execute(Account account, double amount) {
        accountWithdrawService.withdraw(amount, account);
        Transaction transaction = new Transaction(
                TransactionType.WITHDRAW,
                account.getId(),
                account.getClientId(),
                amount,
                System.currentTimeMillis()
        );

        transactionDao.addTransaction(transaction);
    }
}
