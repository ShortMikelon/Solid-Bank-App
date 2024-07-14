package kz.asetkenes.solidbankapp.domain.transaction.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Transaction {

    @NonNull private TransactionType transactionType;

    @NonNull private String accountId;

    @NonNull private String clientId;

    @NonNull private double amount;

    @NonNull private long createdAt;

}