package kz.asetkenes.solidbankapp.services.transactions.impl;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.transaction.model.Transaction;
import kz.asetkenes.solidbankapp.services.transactions.TransactionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionListingServiceImpl implements TransactionListingService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionListingServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> getAllTransactionByAccountId(String accountId) {
        return transactionDao.findAll();
    }
}
