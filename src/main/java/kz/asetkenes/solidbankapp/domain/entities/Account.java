package kz.asetkenes.solidbankapp.domain.entities;

public class Account {

    protected AccountType accountType;

    protected String id;

    protected String clientId;

    protected double balance;

    protected final boolean withdrawAllowed;

    public Account(AccountType accountType, String id, String clientId, double balance, boolean withdrawAllowed) {
        this.accountType = accountType;
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
        this.withdrawAllowed = withdrawAllowed;
    }

    public boolean isWithdrawAllowed() {
        return withdrawAllowed;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        throw new IllegalStateException("Not yet implementation");
    }

    @Override
    public String toString() {
        return String.format("Account: { id = %s, clientId = %s, balance = %.2f }", this.id, this.clientId, this.balance);
    }

}

