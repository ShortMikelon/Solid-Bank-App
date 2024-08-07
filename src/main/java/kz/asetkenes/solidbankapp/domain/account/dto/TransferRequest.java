package kz.asetkenes.solidbankapp.domain.account.dto;

public record TransferRequest(String destinationAccountId, double amount) {
}
