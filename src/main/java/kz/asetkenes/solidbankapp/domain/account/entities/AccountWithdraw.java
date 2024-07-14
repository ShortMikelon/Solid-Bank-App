package kz.asetkenes.solidbankapp.domain.account.entities;

public class AccountWithdraw extends Account {

    public AccountWithdraw(AccountType accountType, String id, String clientId, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientId, balance, withdrawAllowed);
    }

    @Override
    public String toString() {
        return String.format("AccountWithdraw: id = %s, clientId = %s, balance = %.2f", this.id, this.clientId, this.balance);
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }
}