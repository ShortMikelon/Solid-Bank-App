package kz.asetkenes.solidbankapp.domain.transaction.dto;

import kz.asetkenes.solidbankapp.domain.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionResponse {

    private long id;

    private TransactionType transactionType;

    private String accountId;

    private String clientId;

    private double amount;

    private String createdAt;
}
