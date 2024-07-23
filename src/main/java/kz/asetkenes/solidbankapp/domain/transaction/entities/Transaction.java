package kz.asetkenes.solidbankapp.domain.transaction.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "client_id")
    private String clientId;

    private double amount;

    @Column(name = "created_at")
    private long createdAt;

}