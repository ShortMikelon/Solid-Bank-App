package kz.asetkenes.solidbankapp.domain.account.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kz.asetkenes.solidbankapp.exception.InsufficientFundsException;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("deposit")
public abstract class AccountDeposit extends Account {

    public AccountDeposit(
            String id,
            AccountType accountType,
            String clientId,
            double balance,
            boolean withdrawAllowed
    ) {
        super(id, accountType, clientId, balance, withdrawAllowed);
    }

    @Override
    public void setBalance(double balance) {
        if (balance < this.balance) throw new InsufficientFundsException("");
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format(
                "Account Deposit: { id = %s, clientId = %s }",
                this.id,
                this.clientId
        );
    }
}


