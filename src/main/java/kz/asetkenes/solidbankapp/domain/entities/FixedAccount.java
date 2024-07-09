package kz.asetkenes.solidbankapp.domain.entities;

public final class FixedAccount extends AccountDeposit {

    public FixedAccount(AccountType accountType, String id, String clientId, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientId, balance, withdrawAllowed);
    }

    @Override
    public String toString() {
        return String.format("Fixed Account: { id = %s, clientId = %s, balance = %.2f", this.id, this.clientId, this.balance);
    }

}
