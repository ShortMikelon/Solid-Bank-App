package kz.asetkenes.solidbankapp.services.transactions;

import kz.asetkenes.solidbankapp.domain.transaction.model.Transaction;
import kz.asetkenes.solidbankapp.domain.transaction.model.TransactionType;

public interface ITransactionCreateService {

    Transaction save(TransactionType type, String accountId, String clientId, double amount);
}


