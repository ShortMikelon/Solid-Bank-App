package kz.asetkenes.solidbankapp.domain.account.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Account {

    protected AccountType accountType;

    protected String id;

    protected String clientId;

    protected double balance;

    @Getter(AccessLevel.NONE) protected final boolean withdrawAllowed;

    public boolean isWithdrawAllowed() {
        return withdrawAllowed;
    }


    @Override
    public String toString() {
        return String.format("Account: { id = %s, clientId = %s, balance = %.2f }", this.id, this.clientId, this.balance);
    }

}

