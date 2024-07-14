package kz.asetkenes.solidbankapp.data.transactions.impl;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryTransactionDao implements TransactionDao {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public List<Transaction> getTransaction() {
        return transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) return;

        transactions.add(transaction);
    }
}
