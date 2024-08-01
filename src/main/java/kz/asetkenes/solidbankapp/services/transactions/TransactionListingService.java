package kz.asetkenes.solidbankapp.services.transactions;

import kz.asetkenes.solidbankapp.data.transactions.TransactionDao;
import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TransactionListingService {

    List<Transaction> getAllTransactionByAccountId(String accountId);
}

