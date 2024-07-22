package kz.asetkenes.solidbankapp.domain.account.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("fixed")
public final class FixedAccount extends AccountDeposit {

    public FixedAccount(
            AccountType accountType,
            String id,
            String clientId,
            double balance,
            boolean withdrawAllowed
    ) {
        super(id, accountType, clientId, balance, withdrawAllowed);
    }

    @Override
    public String toString() {
        return String.format(
                "Fixed Account: { id = %s, clientId = %s, balance = %.2f",
                this.id,
                this.clientId,
                this.balance
        );
    }

}
