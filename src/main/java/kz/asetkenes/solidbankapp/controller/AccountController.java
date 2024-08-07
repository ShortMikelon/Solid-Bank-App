package kz.asetkenes.solidbankapp.controller;

import kz.asetkenes.solidbankapp.domain.account.BankCore;
import kz.asetkenes.solidbankapp.domain.account.dto.*;
import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.domain.dto.MessageResponse;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionDeposit;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionWithdraw;
import kz.asetkenes.solidbankapp.domain.transaction.TransferUseCase;
import kz.asetkenes.solidbankapp.domain.transaction.dto.TransactionResponse;
import kz.asetkenes.solidbankapp.domain.transaction.model.Transaction;
import kz.asetkenes.solidbankapp.exception.InvalidAccountTypeException;
import kz.asetkenes.solidbankapp.security.JwtAuthenticationFilter;
import kz.asetkenes.solidbankapp.services.account.AccountDeletingService;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import kz.asetkenes.solidbankapp.services.jwt.JwtService;
import kz.asetkenes.solidbankapp.services.transactions.TransactionListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountListingService accountListingService;

    private final AccountDeletingService deletingService;

    private final BankCore bankCore;

    private final TransactionWithdraw withdraw;

    private final TransactionDeposit deposit;

    private final TransactionListingService transactionListingService;

    private final TransferUseCase transferUseCase;

    private final JwtService jwtService;

    @GetMapping
    public List<Account> getAllAccounts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        return accountListingService.getClientAccounts(clientId);
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(
            @RequestBody CreateAccountRequestBody createAccountRequestBody,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        Account newAccount = bankCore.execute(createAccountRequestBody.getAccountType(), clientId);
        return ResponseEntity.ok(new CreateAccountResponse(newAccount.getId()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccountById(
            @PathVariable String accountId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        Account account = accountListingService.getClientAccount(clientId, accountId);
        return ResponseEntity.ok(createAccountResponse(account));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<MessageResponse> deleteAccount(
            @PathVariable String accountId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        Account account = accountListingService.getClientAccount(clientId, accountId);

        deletingService.delete(account);
        return ResponseEntity.ok(new MessageResponse("Account " + accountId + " deleted"));
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<MessageResponse> withdraw(
            @RequestBody DepositWithdrawRequest requestBody,
            @PathVariable String accountId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        Account accountWithdraw = accountListingService.getClientAccount(clientId, accountId);
        withdraw.execute(accountWithdraw, requestBody.getAmount());

        return ResponseEntity.ok(new MessageResponse("Withdraw completed successfully"));

    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<MessageResponse> deposit(
            @PathVariable String accountId,
            @RequestBody DepositWithdrawRequest amount,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        Account accountDeposit = accountListingService.getClientAccount(clientId, accountId);
        deposit.execute(accountDeposit, amount.getAmount());

        return ResponseEntity.ok(new MessageResponse("Deposit completed successfully"));

    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getTransactionsByAccountId(
            @PathVariable String accountId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        accountListingService.getClientAccount(clientId, accountId);

        List<Transaction> transactionsByAccount =
                transactionListingService.getAllTransactionByAccountId(accountId);

        List<TransactionResponse> transactionResponses = transactionsByAccount
                .stream()
                .map(this::createTransactionResponse)
                .toList();

        return ResponseEntity.ok(transactionResponses);
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<MessageResponse> transfer(
            @RequestBody TransferRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable String accountId
    ) {
        String jwt = token.substring(JwtAuthenticationFilter.BEARER_PREFIX.length());
        String clientId = String.valueOf(jwtService.extractId(jwt));

        Account sourceAccount = accountListingService.getClientAccount(clientId, accountId);
        Account destinationAccount = accountListingService.getClientAccount(clientId, request.destinationAccountId());

        transferUseCase.execute(sourceAccount, destinationAccount, request.amount());

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    @ExceptionHandler(InvalidAccountTypeException.class)
    public ResponseEntity<MessageResponse> handleInvalidAccountTypeException() {
        return ResponseEntity.badRequest().body(new MessageResponse("Invalid account type"));
    }

    private AccountResponse createAccountResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountType(),
                account.getClientId(),
                account.getBalance(),
                account.isWithdrawAllowed()
        );
    }

    private TransactionResponse createTransactionResponse(Transaction transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy HH:mm:ss");
        Instant date = Instant.ofEpochMilli(transaction.getCreatedAt());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date, ZoneId.systemDefault());
        String dateStr = formatter.format(localDateTime);

        return new TransactionResponse(
                transaction.getId(),
                transaction.getTransactionType(),
                transaction.getAccountId(),
                transaction.getClientId(),
                transaction.getAmount(),
                dateStr
        );
    }
}