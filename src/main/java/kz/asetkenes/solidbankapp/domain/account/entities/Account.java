package kz.asetkenes.solidbankapp.domain.account.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("account")
public abstract class Account {

    @Id
    @Column(name = "account_id")
    protected String id;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    protected AccountType accountType;

    @Column(name = "client_id")
    protected String clientId;

    protected double balance;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name = "withdraw_allowed")
    protected boolean withdrawAllowed;

    public boolean isWithdrawAllowed() {
        return withdrawAllowed;
    }


    @Override
    public String toString() {
        return String.format(
                "Account: { id = %s, clientId = %s, balance = %.2f }",
                this.id,
                this.clientId,
                this.balance
        );
    }

}

