package kz.asetkenes.solidbankapp.domain.transaction;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.domain.transaction.model.Transaction;
import kz.asetkenes.solidbankapp.domain.transaction.model.TransactionType;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.exception.InsufficientFundsException;
import kz.asetkenes.solidbankapp.exception.NegativeAmountException;
import kz.asetkenes.solidbankapp.exception.WithdrawNotAllowedException;
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
        if (account == null) throw new AccountNotFoundException();
        if (!account.isWithdrawAllowed()) throw new WithdrawNotAllowedException();
        if (amount < 0) throw new NegativeAmountException("Amount is negative");
        if (amount > account.getBalance())
            throw new InsufficientFundsException("Amount more than balance");

        accountWithdrawService.withdraw(amount, account);

        Transaction transaction = new Transaction(
                0L,
                TransactionType.WITHDRAW,
                account.getId(),
                account.getClientId(),
                amount,
                System.currentTimeMillis()
        );

        transactionDao.save(transaction);
    }
}