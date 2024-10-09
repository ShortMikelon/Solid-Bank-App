package kz.asetkenes.solidbankapp.controller;

import kz.asetkenes.solidbankapp.domain.dto.MessageResponse;
import kz.asetkenes.solidbankapp.exception.AccountNotFoundException;
import kz.asetkenes.solidbankapp.exception.InsufficientFundsException;
import kz.asetkenes.solidbankapp.exception.NegativeAmountException;
import kz.asetkenes.solidbankapp.exception.WithdrawNotAllowedException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<MessageResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        return createBadRequestWithMessage("Account not founded");
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<MessageResponse> handleInsufficientFundsException() {
        return createBadRequestWithMessage("The amount is more than the balance");
    }

    @ExceptionHandler(NegativeAmountException.class)
    public ResponseEntity<MessageResponse> handleNegativeAmountException() {
        return createBadRequestWithMessage("Amount is negative");
    }

    @ExceptionHandler(WithdrawNotAllowedException.class)
    public ResponseEntity<MessageResponse> handleWithdrawNotAllowedException() {
        return createBadRequestWithMessage("Withdrawal declined");
    }

    private @NonNull ResponseEntity<MessageResponse> createBadRequestWithMessage(String message) {
        MessageResponse response = new MessageResponse(message);
        return ResponseEntity.badRequest().body(response);
    }
}
