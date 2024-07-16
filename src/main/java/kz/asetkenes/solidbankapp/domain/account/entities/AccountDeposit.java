package kz.asetkenes.solidbankapp.domain.account.entities;

import kz.asetkenes.solidbankapp.exception.InsufficientFundsException;

public class AccountDeposit extends Account {

    public AccountDeposit(AccountType accountType, String id, String clientId, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientId, balance, withdrawAllowed);
    }

    @Override
    public void setBalance(double balance) {
        if (balance < this.balance) throw new InsufficientFundsException("");
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Account Deposit: { id = %s, clientId = %s }", this.id, this.clientId);
    }
}

