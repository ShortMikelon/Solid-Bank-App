package kz.asetkenes.solidbankapp.domain.account.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("saving")
public class SavingAccount extends AccountWithdraw {

    public SavingAccount(AccountType accountType, String id, String clientId, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientId, balance, withdrawAllowed);
    }

    @Override
    public String toString() {
        return String.format("Saving account: { id = %s, clientId = %s, balance = %.2f }", this.id, this.clientId, this.balance);
    }
}
