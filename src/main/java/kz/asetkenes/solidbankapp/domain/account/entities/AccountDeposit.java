package kz.asetkenes.solidbankapp.domain.account.entities;

public class AccountDeposit extends Account {

    public AccountDeposit(AccountType accountType, String id, String clientId, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientId, balance, withdrawAllowed);
    }

    @Override
    public void setBalance(double balance) {
        throw new IllegalStateException("This is Deposit account");
    }

    @Override
    public String toString() {
        return String.format("Account Deposit: { id = %s, clientId = %s }", this.id, this.clientId);
    }
}

