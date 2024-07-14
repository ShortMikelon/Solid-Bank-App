package kz.asetkenes.solidbankapp.data.transactions;

import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> getTransaction();

    void addTransaction(Transaction transaction);

}

