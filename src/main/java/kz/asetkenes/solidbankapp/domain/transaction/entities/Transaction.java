package kz.asetkenes.solidbankapp.domain.transaction.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Transaction {

    @NonNull private TransactionType transactionType;

    @NonNull private String accountId;

    @NonNull private String clientId;

    private double amount;

    private long createdAt;

}