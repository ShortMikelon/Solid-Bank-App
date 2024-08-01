package kz.asetkenes.solidbankapp.controller;

import kz.asetkenes.solidbankapp.controller.entities.AccountAmountRequestBody;
import kz.asetkenes.solidbankapp.controller.entities.CreateAccountRequestBody;
import kz.asetkenes.solidbankapp.domain.account.BankCore;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionDeposit;
import kz.asetkenes.solidbankapp.domain.transaction.TransactionWithdraw;
import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import kz.asetkenes.solidbankapp.exception.*;
import kz.asetkenes.solidbankapp.services.account.AccountDeletingService;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import kz.asetkenes.solidbankapp.services.transactions.TransactionListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountListingService accountListingService;

    private final AccountDeletingService deletingService;

    private final BankCore bankCore;

    private final TransactionWithdraw withdraw;

    private final TransactionDeposit deposit;

    private final TransactionListingService transactionListingService;

    @Autowired
    public AccountController(
            AccountListingService accountListingService,
            AccountDeletingService deletingService,
            BankCore bankCore,
            TransactionWithdraw withdraw,
            TransactionDeposit deposit,
            TransactionListingService transactionListingService
    ) {
        this.accountListingService = accountListingService;
        this.deletingService = deletingService;
        this.bankCore = bankCore;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.transactionListingService = transactionListingService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountListingService.getClientAccounts(CLIENT_ID);
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequestBody createAccountRequestBody) {
        try {
            Account newAccount = bankCore.execute(createAccountRequestBody.getAccountType(), CLIENT_ID);
            return ResponseEntity.ok(newAccount.getId());
        } catch (InvalidAccountTypeException ex) {
            return ResponseEntity.badRequest().body("Invalid account type");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unknown server error");
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable String accountId) {
        try {
            return ResponseEntity.ok(accountListingService.getClientAccount(CLIENT_ID, accountId));
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.badRequest().body("Account not founded");
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable String accountId) {
        try {
            deletingService.deleteById(accountId);

            return ResponseEntity.ok("Account " + accountId + " deleted");
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.badRequest().body("Account not founded");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unknown server error");
        }
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdraw(
            @RequestBody AccountAmountRequestBody requestBody,
            @PathVariable String accountId
    ) {
        try {
            Account accountWithdraw = accountListingService.getClientAccount(CLIENT_ID, accountId);
            withdraw.execute(accountWithdraw, requestBody.getAmount());

            return ResponseEntity.ok("Withdraw completed successfully");
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.badRequest().body("Account not founded");
        } catch (InsufficientFundsException ex) {
            return ResponseEntity.badRequest().body("The amount is more than the balance");
        } catch (NegativeAmountException ex) {
            return ResponseEntity.badRequest().body("Amount is negative");
        } catch (WithdrawalNotAllowedException ex) {
            return ResponseEntity
                    .badRequest()
                    .body("You can't withdraw money from a fixed account");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unknown server error");
        }
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> deposit(
            @PathVariable String accountId,
            @RequestBody AccountAmountRequestBody amount
    ) {
        try {
            Account accountDeposit = accountListingService.getClientAccount(CLIENT_ID, accountId);
            System.out.println("Account: " + amount);
            deposit.execute(accountDeposit, amount.getAmount());

            return ResponseEntity.ok("Deposit completed successfully");
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.badRequest().body("Account not founded");
        } catch (NegativeAmountException ex) {
            return ResponseEntity.badRequest().body("Amount is negative");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unknown server error");
        }
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getTransactionsByAccountId(@PathVariable String accountId) {
        try {
            accountListingService.getClientAccount(CLIENT_ID, accountId);

            List<Transaction> transactionsByAccount =
                    transactionListingService.getAllTransactionByAccountId(accountId);

            return ResponseEntity.ok(transactionsByAccount);
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.badRequest().body("Account not founded");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unknown server error");
        }
    }

    private static final String CLIENT_ID = "0";
}
