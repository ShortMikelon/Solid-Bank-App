package kz.asetkenes.solidbankapp.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException() {
        this("Account not founded");
    }
}

