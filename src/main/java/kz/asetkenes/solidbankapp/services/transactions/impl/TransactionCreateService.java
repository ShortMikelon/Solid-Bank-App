package kz.asetkenes.solidbankapp.services.transactions.impl;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.transaction.model.Transaction;
import kz.asetkenes.solidbankapp.domain.transaction.model.TransactionType;
import kz.asetkenes.solidbankapp.services.transactions.ITransactionCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCreateService implements ITransactionCreateService {

    private final TransactionDao transactionDao;

    @Override
    public Transaction save(TransactionType type, String accountId, String clientId, double amount) {
        Transaction newTransaction = new Transaction(null, type, accountId, clientId, amount, System.currentTimeMillis());

        return transactionDao.save(newTransaction);
    }
}
