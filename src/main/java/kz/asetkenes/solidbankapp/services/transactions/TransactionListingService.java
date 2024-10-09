package kz.asetkenes.solidbankapp.services.transactions;

import kz.asetkenes.solidbankapp.domain.transaction.model.Transaction;

import java.util.List;

public interface TransactionListingService {

    List<Transaction> getAllTransactionByAccountId(String accountId);
}

