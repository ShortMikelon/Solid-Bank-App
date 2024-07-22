package kz.asetkenes.solidbankapp.domain.account.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("checking")
public class CheckingAccount extends AccountWithdraw {

    public CheckingAccount(
            AccountType accountType,
            String id,
            String clientId,
            double balance,
            boolean withdrawAllowed
    ) {
        super(accountType, id, clientId, balance, withdrawAllowed);
    }

    @Override
    public String toString() {
        return String.format(
                "Checking Account: { id = %s, clientId = %s, balance = %.2f }",
                this.id,
                this.clientId,
                this.balance
        );
    }
}
